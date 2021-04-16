package mobg5.g51999.foodrating.network

import mobg5.g51999.foodrating.business.api.FoodServiceAPIResponse
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Contract to the requests to the REST API.
 */
interface DurableFoodApiService
{
    @GET("/api/records/1.0/search/?dataset=manger-et-boire-durablement&rows=100")
    suspend fun getAllData(): FoodServiceAPIResponse

    @GET("/api/records/1.0/search/?dataset=manger-et-boire-durablement&rows=100")
    suspend fun getDataByCategory(@Query("q") category: String): FoodServiceAPIResponse
}


