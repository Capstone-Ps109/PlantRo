package com.example.plantro.data.remote.response


import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class RegisterResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null
)

data class LoginResponse (
    @field:SerializedName("user")
    val user: User? = null,

    @field:SerializedName("token")
    val token: String
//    @field:SerializedName("loginResult")
//    val loginResult: LoginResult,
//
//    @field:SerializedName("error")
//    val error: Boolean,
//
//    @field:SerializedName("message")
//    val message: String
)

data class User(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("email")
    val email: String? = null
//    @field:SerializedName("name")
//    val name: String,
//
//    @field:SerializedName("userId")
//    val userId: String,
//
//    @field:SerializedName("token")
//    val token: String
)