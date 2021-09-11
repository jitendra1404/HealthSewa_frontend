package com.example.healthSewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthSewa.Entity.Appointment
import com.example.healthSewa.Repository.AppointmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateAppointmentActivity : AppCompatActivity(){

    private lateinit var DeviceName: EditText
    private lateinit var DeviceModel: EditText
    private lateinit var AppointmentDate: EditText
    private lateinit var Location: EditText
    private lateinit var Issue: EditText
    private lateinit var Edit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_appointment)


        DeviceName = findViewById(R.id.etDeviceName)
        DeviceModel = findViewById(R.id.etDeviceModel)
        AppointmentDate = findViewById(R.id.etAppointmentDate)
        Location = findViewById(R.id.etLocation)
        Issue = findViewById(R.id.etIssue)
        Edit = findViewById(R.id.btnEdit)

        val appointment = intent.getParcelableExtra<Appointment>("Appointment")
        if (appointment != null) {
            DeviceName.setText(appointment.weight)
            DeviceModel.setText(appointment.healthissue)
            AppointmentDate.setText(appointment.age)
            Location.setText(appointment.status)
            Issue.setText(appointment.date)
        }

        Edit.setOnClickListener {
            updateAppointment()

        }
    }

    private fun updateAppointment() {
        val intent = intent.getParcelableExtra<Appointment>("Appointment")
        val devicename = DeviceName.text.toString()
        val devicemodel = DeviceModel.text.toString()
        val appointmentdate = AppointmentDate.text.toString()
        val location= Location.text.toString()
        val issue = Issue.text.toString()
        val appointment = Appointment(
            weight = devicename,
            healthissue = devicemodel,
            age = appointmentdate,
            status = location,
            date = issue
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.putAppointment(intent?._id!!, appointment)
                if (response.success ==true) {
                    //for update image
//                    if (imageUrl != null) {
//                        updatefoodimage(intent?._id!!)
//                    }
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@UpdateAppointmentActivity,
                            " Appointment has been updated", Toast.LENGTH_SHORT
                        ).show()
                    } 
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@UpdateAppointmentActivity,
                        ex.localizedMessage, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}