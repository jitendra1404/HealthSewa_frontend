package com.example.healthSewa.dao

import androidx.room.*
import com.example.healthSewa.Entity.Appointment

@Dao
interface AppointmentDAO {

    @Insert
    suspend fun insertAppointment(appointment: Appointment)

   @Query("SELECT * FROM Appointment")
   suspend fun getAllAppointment():MutableList<Appointment>

   @Update
   suspend fun updateAppointment(appointment: Appointment)

   @Delete
   suspend fun deleteAppointment(appointment: Appointment)
//
}