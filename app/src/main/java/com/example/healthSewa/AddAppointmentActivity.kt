package com.example.healthSewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.healthSewa.Entity.Appointment
import com.example.healthSewa.Repository.AppointmentRepository
import com.example.healthSewa.db.AppointmentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddAppointmentActivity : AppCompatActivity()
//    ,SensorEventListener
{

//    private lateinit var sensorManager:SensorManager
//    private lateinit var LightSensor:Sensor

    private lateinit var ethealthissue: EditText
    private lateinit var etoccupation: EditText
    private lateinit var etdate: EditText
    private lateinit var etage: EditText
    private lateinit var etconsultanthour: EditText
    private lateinit var etbehaviors: EditText
    private lateinit var etstatement: EditText
    private lateinit var btnsubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        ethealthissue=findViewById(R.id.ethealthissue)
        etoccupation=findViewById(R.id.etoccupation)
        etdate=findViewById(R.id.etdate)
        etage=findViewById(R.id.etage)
        etconsultanthour=findViewById(R.id.etconsultanthour)
        etbehaviors=findViewById(R.id.etbehaviors)
        etstatement=findViewById(R.id.etstatement)
        btnsubmit=findViewById(R.id.btnsubmit)

//        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
//        LightSensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

        btnsubmit.setOnClickListener {
            addAppointment()
        }

    }

    private fun addAppointment() {
        val healthissue = ethealthissue.text.toString()
        val occupation = etoccupation.text.toString()
        val date = etdate.text.toString()
        val age = etage.text.toString()
        val consultanthour = etconsultanthour.text.toString()
        val behaviors= etbehaviors.text.toString()
        val statement= etstatement.text.toString()

        val appointment = Appointment(
            HealthIssue = healthissue,
            Occupation = occupation,
            Date = date,
            Age= age,
            ConsultantHour = consultanthour,
            Behaviors = behaviors,
            Statement = statement
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {

                AppointmentDB
                    .getInstance(this@AddAppointmentActivity)
                    .getAppointmentDAO()
                    .insertAppointment(appointment)

                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.addAppointment(appointment)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddAppointmentActivity,
                            "New Appointment Added Successfully",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddAppointmentActivity,
                        "Error ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        }

    }

//    override fun onResume() {
//        super.onResume()
//        sensorManager!!.registerListener(this,LightSensor,SensorManager.SENSOR_DELAY_NORMAL)
//    }
//    override fun onPause() {
//        super.onPause()
//        sensorManager!!.unregisterListener(this)
//    }
//
//    var isRunning=false
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        try {
//            if (event!!.values[0]<20 && !isRunning) {
//                isRunning=true
//                window.decorView.setBackgroundColor(Color. GRAY)
//            }else{isRunning = false
//                window.decorView.setBackgroundColor(Color.YELLOW)
//            }
//        }catch (e: IOException){}
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }

}

