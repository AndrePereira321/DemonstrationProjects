package mobg5.g51999.foodrating.business

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

/**
 * Represents a feedback of a place given by a user.
 */
@Entity
data class UserFeedback(
    @ColumnInfo(name = "userEmail") val userEmail: String,
    @ColumnInfo(name = "recordId") val recordId: String,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "comment") val comment: String,
    @ColumnInfo(name = "date_added") val date_added: Timestamp
)
{
    @PrimaryKey(autoGenerate = true)
    var feedbackId: Int = 0

    /**
     * A french text describing the rating.
     */
    val ratingFr: String
        get() = when (rating)
        {
            1 -> "Horrible"
            2 -> "Mauvais"
            3 -> "Raisonable"
            4 -> "Bien"
            5 -> "Parfait"
            else -> ""
        }

    override fun equals(other: Any?): Boolean
    {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserFeedback

        if (userEmail != other.userEmail) return false
        if (recordId != other.recordId) return false
        if (rating != other.rating) return false
        if (comment != other.comment) return false
        if (date_added != other.date_added) return false
        if (feedbackId != other.feedbackId) return false

        return true
    }

    override fun hashCode(): Int
    {
        var result = userEmail.hashCode()
        result = 31 * result + recordId.hashCode()
        result = 31 * result + rating
        result = 31 * result + comment.hashCode()
        result = 31 * result + date_added.hashCode()
        result = 31 * result + feedbackId
        return result
    }
}