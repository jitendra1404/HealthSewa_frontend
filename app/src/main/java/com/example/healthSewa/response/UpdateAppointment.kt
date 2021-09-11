package com.example.healthSewa.response

import com.example.healthSewa.Entity.Appointment

data class UpdateAppointment (
    val success:Boolean?=null,
    val data: Appointment?=null
)
