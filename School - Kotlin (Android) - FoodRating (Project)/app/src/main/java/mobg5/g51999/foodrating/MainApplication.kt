@file:Suppress("unused")

package mobg5.g51999.foodrating

import android.app.Application
import androidx.room.Room
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mobg5.g51999.foodrating.business.User
import mobg5.g51999.foodrating.dao.UserFeedbackDao
import mobg5.g51999.foodrating.database.AppDatabase
import mobg5.g51999.foodrating.network.DurableFoodApiService
import mobg5.g51999.foodrating.business.api.RecordsProperty
import mobg5.g51999.foodrating.repository.Repository
import mobg5.g51999.foodrating.screens.detailpage.DetailPageViewModel
import mobg5.g51999.foodrating.screens.lobby.LobbyViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainApplication : Application()
{

    /**
     * Firebase online storage.
     * Used to save images.
     */
    private val fireBaseStorageModule = module {
        fun provideStorage(): FirebaseStorage = Firebase.storage
        single {provideStorage()}
    }

    /**
     * Room SQLite Database.
     * Used to save the users records.
     */
    private val localDatabaseModule = module {
        fun provideDatabase(application: Application): AppDatabase
        {
            return Room.databaseBuilder(
                application, AppDatabase::class.java, "food_rating_database"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        fun provideFeedbackDao(database: AppDatabase): UserFeedbackDao
        {
            return database.userFeedbackDao
        }

        single { provideDatabase(androidApplication()) }
        single { provideFeedbackDao(get()) }
    }

    /**
     * Retrofit REST client (with moshi parser).
     * Used to retrieve the places with durable food.
     */
    private val networkModule = module {

        val baseURL = "https://opendata.brussels.be/"

        /**
         * Converter of JSON to Kotlin Objects.
         */
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        /**
         * Retrofit REST Client
         */
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseURL)
            .build()

        /**
         * Implementation of the API requests.
         */
        single { retrofit.create(DurableFoodApiService::class.java) }
    }

    /**
     * Regroups all the other modules.
     */
    private val repositoryModule = module {
        single { Repository(get(), get(), get()) }
    }

    /**
     * Instanciate the view models.
     */
    private val viewModelsModule = module {
        //Shared ViewModel Singleton
        single { (connectedUser: User) -> LobbyViewModel(connectedUser, get()) }
        //Injects arguments without factories
        viewModel { (connectedUser: User, record: RecordsProperty) ->
            DetailPageViewModel(connectedUser, record, get())
        }
    }

    /**
     * Creates KoinApplication
     */
    override fun onCreate()
    {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(
                viewModelsModule,
                localDatabaseModule,
                networkModule,
                fireBaseStorageModule,
                repositoryModule
            ))
        }
    }

}
