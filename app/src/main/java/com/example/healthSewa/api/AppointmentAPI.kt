package com.example.healthSewa.api

import com.example.healthSewa.Entity.Appointment
import com.example.healthSewa.response.AddAppointmentResponse
import com.example.healthSewa.response.DeleteAppointmentResponse
import com.example.healthSewa.response.GetAllAppointmentResponse
import com.example.healthSewa.response.UpdateAppointment
import retrofit2.Response
import retrofit2.http.*

interface AppointmentAPI {

    //Add Appointment
    @POST("Appointment/insert")
    suspend fun addAppointment(
        @Header("Authorization") token:String,
        @Body appointment: Appointment
    ): Response<AddAppointmentResponse>

    //Delete Appointment
    @DELETE("Appointment/delete/{id}")
    suspend fun deleteAppointment(
        @Header("Authorization") token: String,
        @Path("id")id:String
    ): Response<DeleteAppointmentResponse>

    //GET all Appointment
    @GET("Appointment/All")
    suspend fun getAllAppointment(
        @Header("Authorization") token : String,
    ): Response<GetAllAppointmentResponse>

    // Update  Appointment
    @PUT("Appointment/update/{id}")
    suspend fun putAppointment(
//        @Header("Authorization") token : String,
        @Path("id")id:String,
        @Body appointment: Appointment
    ): Response<UpdateAppointment>

//    //upload image
//    @Multipart
//    @PUT("donor/{id}/photo")
//    suspend fun uploadImage(
//        @Header("Authorization") token: String,
//        @Path("id") id: String,
//        @Part file: MultipartBody.Part
//    ): Response<ImageResponse>
}