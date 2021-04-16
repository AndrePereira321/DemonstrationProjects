package mobg5.g51999.foodrating.business.api

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Coordinates of a place.
 */
data class GeometryProperty (

	/**
	 * Type, almost always 2D.
	 */
	@Json( name="type") val type : String,
	@Json( name="coordinates") val coordinates : List<Double>
) : Serializable
{
	override fun equals(other: Any?): Boolean
	{
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as GeometryProperty

		if (type != other.type) return false
		if (coordinates != other.coordinates) return false

		return true
	}

	override fun hashCode(): Int
	{
		var result = type.hashCode()
		result = 31 * result + coordinates.hashCode()
		return result
	}
}