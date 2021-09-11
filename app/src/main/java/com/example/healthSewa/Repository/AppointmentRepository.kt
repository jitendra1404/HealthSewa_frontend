package com.example.healthSewa.Repository

import com.example.healthSewa.Entity.Appointment
import com.example.healthSewa.api.MyAPIRequest
import com.example.healthSewa.api.ServiceBuilder
import com.example.healthSewa.api.AppointmentAPI
import com.example.healthSewa.response.AddAppointmentResponse
import com.example.healthSewa.response.DeleteAppointmentResponse
import com.example.healthSewa.response.GetAllAppointmentResponse
import com.example.healthSewa.response.UpdateAppointment

class AppointmentRepository:MyAPIRequest() {

    private val AppointmentAPI =
        ServiceBuilder.buildServices(AppointmentAPI::class.java)

    //Add Appointment
    suspend fun addAppointment(appointment: Appointment): AddAppointmentResponse {
        return apiRequest {
            AppointmentAPI.addAppointment(
                ServiceBuilder.token!!, appointment
            )
        }
    }

    //Delete Appointment

    suspend fun deleteAppointment(id: String): DeleteAppointmentResponse {
        return apiRequest {
            AppointmentAPI.deleteAppointment(
                ServiceBuilder.token!!, id
            )
        }
    }

    // Get Appointment

    suspend fun getAllAppointment(): GetAllAppointmentResponse {
        return apiRequest {
            AppointmentAPI.getAllAppointment(
                ServiceBuilder.token!!
            )
        }
    }

    suspend fun putAppointment(id: String, appointment: Appointment): UpdateAppointment {
        return apiRequest {
            AppointmentAPI.putAppointment(
              id,appointment
            )
        }
    }


//    //upload image
//    suspend fun uploadImage(id: String,body: MultipartBody.Part)
//            : ImageResponse {
//        return apiRequest {
//            DonorAPI.uploadImage(ServiceBuilder.token!!,id,body)
//        }
//    }
//}

}