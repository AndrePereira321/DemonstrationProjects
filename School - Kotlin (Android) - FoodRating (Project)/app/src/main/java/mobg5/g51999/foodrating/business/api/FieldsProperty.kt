package mobg5.g51999.foodrating.business.api

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Description of the place.
 */
data class FieldsProperty (

	@Json( name="categorie") val categorie : String,
	@Json( name="nom") val nom : String,
	@Json( name="type") val type : String,
	@Json( name="geo_point_2d") val geoPoint2D : List<Double>?,
	@Json( name="adresse") val adresse : String?,
	@Json( name="site_nl") val siteNl : String?,
	@Json( name="site_fr") val siteFr : String?
) : Serializable
{
	override fun equals(other: Any?): Boolean
	{
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as FieldsProperty

		if (categorie != other.categorie) return false
		if (nom != other.nom) return false
		if (type != other.type) return false
		if (geoPoint2D != other.geoPoint2D) return false
		if (adresse != other.adresse) return false
		if (siteNl != other.siteNl) return false
		if (siteFr != other.siteFr) return false

		return true
	}

	override fun hashCode(): Int
	{
		var result = categorie.hashCode()
		result = 31 * result + nom.hashCode()
		result = 31 * result + type.hashCode()
		result = 31 * result + (geoPoint2D?.hashCode() ?: 0)
		result = 31 * result + (adresse?.hashCode() ?: 0)
		result = 31 * result + (siteNl?.hashCode() ?: 0)
		result = 31 * result + (siteFr?.hashCode() ?: 0)
		return result
	}
}