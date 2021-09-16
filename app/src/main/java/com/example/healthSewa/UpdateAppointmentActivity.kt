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

    private lateinit var HealthIssue: EditText
    private lateinit var Occupation: EditText
    private lateinit var Date: EditText
    private lateinit var Age: EditText
    private lateinit var ConsultantHour: EditText
    private lateinit var Behaviors: EditText
    private lateinit var Statement: EditText
    private lateinit var Edit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_appointment)


        HealthIssue = findViewById(R.id.etHealthIssue)
        Occupation = findViewById(R.id.etOccupation)
        Date = findViewById(R.id.etDate)
        Age = findViewById(R.id.etAge)
        ConsultantHour = findViewById(R.id.etConsultantHour)
        Behaviors = findViewById(R.id.etBehaviors)
        Statement = findViewById(R.id.etStatement)
        Edit = findViewById(R.id.btnEdit)

        val appointment = intent.getParcelableExtra<Appointment>("Appointment")
        if (appointment != null) {
            HealthIssue.setText(appointment.HealthIssue)
            Occupation.setText(appointment.Occupation)
            Date.setText(appointment.Date)
            Age.setText(appointment.Age)
            ConsultantHour.setText(appointment.ConsultantHour)
            Behaviors.setText(appointment.Behaviors)
            Statement.setText(appointment.Statement)
        }

        Edit.setOnClickListener {
            updateAppointment()

        }
    }

    private fun updateAppointment() {
        val intent = intent.getParcelableExtra<Appointment>("Appointment")
        val healthissue = HealthIssue.text.toString()
        val occupation = Occupation.text.toString()
        val date = Date.text.toString()
        val age= Age.text.toString()
        val consultanthour = ConsultantHour.text.toString()
        val behaviors = ConsultantHour.text.toString()
        val statement = Statement.text.toString()
        val appointment = Appointment(

            HealthIssue = healthissue,
            Occupation = occupation,
            Behaviors = behaviors,
            Date = date,
            Age = age,
            ConsultantHour = consultanthour,
            Statement = statement
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.putAppointment(intent?._id!!, appointment)
                if (response.success ==true) {

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