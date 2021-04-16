package mobg5.g51999.foodrating.business.api

import com.squareup.moshi.Json
import java.io.Serializable

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com
 */
/**
 * Represents the main structure of the JSON file of the response of the API request.
 */
data class FoodServiceAPIResponse (

    /**
	 * Number of entries of records.
	 */
	@Json( name="nhits") val nHits: Int,

    /**
	 * Summary of the parameters of the request.
	 */
	@Json( name="parameters") val parametersProperty : ParametersProperty,

    /**
	 * Data of records of durable food places.
	 */
	@Json( name="records") val records : List<RecordsProperty>
): Serializable