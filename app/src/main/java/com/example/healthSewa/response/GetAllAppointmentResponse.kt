package com.example.healthSewa.response

import com.example.healthSewa.Entity.Appointment

data class GetAllAppointmentResponse (

    val success:Boolean?=null,
    val count:Int?=null,
    val data :MutableList<Appointment>?=null

        )
