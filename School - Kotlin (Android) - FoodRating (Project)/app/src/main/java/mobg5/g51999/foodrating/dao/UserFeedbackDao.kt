package mobg5.g51999.foodrating.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mobg5.g51999.foodrating.business.UserFeedback

/**
 * DAO for the user feedbacks.
 */
@Dao
interface UserFeedbackDao
{

    /**
     * Insert a new feedback.
     */
    @Insert
    fun insertFeedback(feedback: UserFeedback)

    /**
     * Retrieve all the feedbacks of the given record.
     */
    @Query("SELECT * FROM UserFeedback WHERE recordId = :recordId")
    fun getRecordFeedBacks(recordId: String): List<UserFeedback>


    /**
     * Retrieves the last feedback of an user for the given record.
     */
    @Query("SELECT * FROM UserFeedback WHERE recordId = :recordId AND userEmail = :userEmail ORDER BY date_added DESC LIMIT 1")
    fun getLastUserFeedbackFromRecord(userEmail: String, recordId: String): UserFeedback?
}