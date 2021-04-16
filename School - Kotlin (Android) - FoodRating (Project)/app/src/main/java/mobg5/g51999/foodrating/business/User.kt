package mobg5.g51999.foodrating.business

import java.io.Serializable
import java.sql.Timestamp

/**
 * Represents a connected user.
 */

data class User(
    val email: String,
    val displayName: String,
    val dateCreated: Timestamp,
    var lastConnection: Timestamp
) : Serializable