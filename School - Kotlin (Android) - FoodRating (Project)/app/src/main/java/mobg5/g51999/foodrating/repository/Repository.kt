package mobg5.g51999.foodrating.repository

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import mobg5.g51999.foodrating.business.UserFeedback
import mobg5.g51999.foodrating.database.AppDatabase
import mobg5.g51999.foodrating.network.DurableFoodApiService
import mobg5.g51999.foodrating.business.api.FoodServiceAPIResponse
import mobg5.g51999.foodrating.business.api.RecordsProperty
import java.io.ByteArrayOutputStream
import java.sql.Timestamp

class Repository(
    private val appDatabase: AppDatabase,
    private val apiService: DurableFoodApiService,
    private val imgStorage: FirebaseStorage
)
{
    suspend fun getRecordsByCategory(_category: String): FoodServiceAPIResponse
    {
        val category = "categorie='$_category'"
        return apiService.getDataByCategory(category)
    }

    suspend fun getAllRecords(): FoodServiceAPIResponse
    {
        return apiService.getAllData()
    }

    /**
     * Retrieve the last feedback of the given user for the given record.
     */
    fun getLastUserFeedbackFromRecord(userEmail: String, recordId: String): UserFeedback?
    {
        return appDatabase.userFeedbackDao.getLastUserFeedbackFromRecord(userEmail, recordId)
    }

    /**
     * Retrieve all the feedbacks from the given record.
     */
    fun getRecordFeedBacks(record: RecordsProperty): MutableList<UserFeedback>
    {
        return appDatabase.userFeedbackDao.getRecordFeedBacks(record.recordid).toMutableList()
    }

    fun insertFeedback(feedback: UserFeedback)
    {
        appDatabase.userFeedbackDao.insertFeedback(feedback)
    }

    /**
     * Inserts a BitMap image into the firebase storage.
     */
    fun insertUserImage(userImage: Bitmap, recordId: String, userEmail: String): UploadTask
    {
        val timestamp = Timestamp(System.currentTimeMillis())
        val byteOutputStream = ByteArrayOutputStream()
        userImage.compress(Bitmap.CompressFormat.JPEG, 100, byteOutputStream)
        val data = byteOutputStream.toByteArray()
        val ref = imgStorage.reference.child("${recordId}/${userEmail}_${timestamp}.jpeg")
        return ref.putBytes(data)
    }

    /**
     * Retrieve the firebase references of a list of a images urls from the given record.
     */
    fun getRecordImagesReferences(recordId: String): Task<ListResult>
    {
        return Firebase.storage.reference.child(recordId).listAll()
    }
}