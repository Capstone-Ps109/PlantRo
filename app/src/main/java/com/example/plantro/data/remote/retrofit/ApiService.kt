
package com.example.plantro.data.remote.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.plantro.data.remote.response.OutputHistory
import com.example.plantro.data.remote.response.OutputResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    data class InputParams(
        val Nitrogen: Int,
        val Phosphorus : Int,
        val Potassium : Int,
        val Temperature  : Float,
        val Humidity  : Float,
        val pH_Value  : Float,
        val Rainfall : Float,
        )
    @POST("/predict?token=")
    suspend fun input(
        @Query("token") token: String,
        @Body params: InputParams): OutputResponse

    @GET("/prediction-history")
    suspend fun history(@Query("token") token: String): OutputHistory

}
