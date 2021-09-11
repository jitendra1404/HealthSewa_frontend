package com.example.healthSewa.api

import com.example.healthSewa.Entity.User
import com.example.healthSewa.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    // register user

    @POST("/user/register")
    suspend fun registerUser(
            @Body user: User
    ): Response<LoginResponse>

    // login user

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun loginUser(

        @Field("custo_mobile") Username: String,
        @Field("custo_password") password: String

    ):Response<LoginResponse>
}