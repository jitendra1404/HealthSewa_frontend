package com.example.healthSewa.api

import com.example.healthSewa.Entity.Review
import com.example.healthSewa.response.*
import retrofit2.Response
import retrofit2.http.*

interface ReviewAPI {

    //Add Review
    @POST("Report/insert")
    suspend fun addReview(
        @Header("Authorization") token:String,
        @Body review:Review
    ): Response<AddReviewResponse>

    //Delete Review
    @DELETE("Report/delete/{id}")
    suspend fun deleteReview(
        @Header("Authorization") token: String,
        @Path("id")id:String
    ): Response<DeleteReviewResponse>

    //GET all Review
    @GET("Report/All")
    suspend fun getAllReview(
        @Header("Authorization") token : String,
    ): Response<GetAllReviewResponse>
}