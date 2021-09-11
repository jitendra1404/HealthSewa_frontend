package com.example.healthSewa.response

import com.example.healthSewa.Entity.Appointment

 data class AddAppointmentResponse(
    val success:Boolean?=null,
    val data:Appointment?=null
)