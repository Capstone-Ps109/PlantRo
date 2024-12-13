package com.example.plantro.data.remote.retrofit

import com.example.plantro.data.remote.response.LoginResponse
import com.example.plantro.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService2 {
    //register service
    @POST("auth/register")
    suspend fun register(@Body body: Map<String, String>): RegisterResponse

    //register service
    @POST("auth/login")
    suspend fun login(@Body body: Map<String, String>): LoginResponse

//    //API SERVICE FOR LOGIN
//    @FormUrlEncoded
//    @POST("auth/login")
//    suspend fun login (
//        @Field("email") email: String,
//        @Field("password") password: String
//    ) : LoginResponse


}