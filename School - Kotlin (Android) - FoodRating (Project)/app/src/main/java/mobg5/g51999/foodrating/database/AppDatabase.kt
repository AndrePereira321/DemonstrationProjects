package mobg5.g51999.foodrating.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mobg5.g51999.foodrating.business.UserFeedback
import mobg5.g51999.foodrating.dao.UserFeedbackDao

/**
 * Singleton database for this app.
 */
@Database(entities = [UserFeedback::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase()
{
    abstract val userFeedbackDao: UserFeedbackDao
}