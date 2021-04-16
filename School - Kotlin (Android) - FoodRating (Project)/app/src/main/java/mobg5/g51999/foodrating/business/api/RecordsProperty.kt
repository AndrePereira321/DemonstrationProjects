package mobg5.g51999.foodrating.business.api

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Data about a place providing durable food.
 */
data class RecordsProperty (

    /**
	 * ID of Data-Set.
	 * Used by opendata, this app always use datasetid='durablefood'
	 */
	@Json( name="datasetid") val datasetid : String,

    /**
	 * ID of the Record
	 */
	@Json( name="recordid") val recordid : String,

    /**
	 * Description about the place.
	 */
	@Json( name="fields") val fieldsProperty : FieldsProperty,

    /**
	 * Coordinates.
	 */
	@Json( name="geometry") val geometryProperty : GeometryProperty?,

    /**
	 * Last modified.
	 */
	@Json( name="record_timestamp") val record_timestamp : String
) : Serializable
{

	/**
	 * Retrieve the coordinates. (the API has 2 fields describing the coordinates)
	 */
	val coordinates : List<Double>?
		get() = geometryProperty?.coordinates ?: fieldsProperty.geoPoint2D

	override fun equals(other: Any?): Boolean
	{
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as RecordsProperty

		if (recordid != other.recordid) return false
		if (record_timestamp != other.record_timestamp) return false

		return true
	}

	override fun hashCode(): Int
	{
		var result = datasetid.hashCode()
		result = 31 * result + recordid.hashCode()
		result = 31 * result + fieldsProperty.hashCode()
		result = 31 * result + (geometryProperty?.hashCode() ?: 0)
		result = 31 * result + record_timestamp.hashCode()
		return result
	}
}