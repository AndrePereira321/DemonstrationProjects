package mobg5.g51999.foodrating.business.api

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Parameters of the REST GET Request.
 */
data class ParametersProperty(

	@Json(name = "dataset") val dataSet: String,
	@Json(name = "timezone") val timeZone: String,
	@Json(name = "rows") val rows: Int,
	@Json(name = "start") val start: Int,
	@Json(name = "format") val format: String
) : Serializable
{
    override fun equals(other: Any?): Boolean
    {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ParametersProperty

        if (dataSet != other.dataSet) return false
        if (timeZone != other.timeZone) return false
        if (rows != other.rows) return false
        if (start != other.start) return false
        if (format != other.format) return false

        return true
    }

    override fun hashCode(): Int
    {
        var result = dataSet.hashCode()
        result = 31 * result + timeZone.hashCode()
        result = 31 * result + rows
        result = 31 * result + start
        result = 31 * result + format.hashCode()
        return result
    }
}