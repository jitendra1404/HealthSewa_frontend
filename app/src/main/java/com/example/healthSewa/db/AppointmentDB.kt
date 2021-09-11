package com.example.healthSewa.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthSewa.Entity.Appointment
import com.example.healthSewa.dao.AppointmentDAO

@Database(
    entities = [(Appointment::class)],
    version = 5,
    exportSchema = false
)

abstract class AppointmentDB:RoomDatabase(){
    abstract fun getAppointmentDAO():AppointmentDAO

    companion object{
        @Volatile
        private var instance : AppointmentDB? = null
        fun getInstance(context: Context): AppointmentDB{
            if (instance==null){
                synchronized(AppointmentDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppointmentDB::class.java,
                "AppointmentDB"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}
