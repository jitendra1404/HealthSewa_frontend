package com.example.healthSewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageButton
import com.example.healthSewa.Fragments.*
import com.example.healthSewa.Fragments.DashboardActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboard: Button
    private lateinit var appointment: Button
    private lateinit var review: Button
    private lateinit var about: Button



    private lateinit var googlemap: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dashboard = findViewById(R.id.btndashboard)
        appointment = findViewById(R.id.btnappointment)
        review = findViewById(R.id.btnreview)
        about = findViewById(R.id.btnabout)


        dashboard.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, DashboardActivity())
                addToBackStack(null)
                commit()
            }
        }
        appointment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, AppointmentActivity())
                addToBackStack(null)
                commit()
            }
        }
        review.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, ReviewActivity())
                addToBackStack(null)
                commit()
            }
        }

        about.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer, AboutActivity())
                addToBackStack(null)
                commit()
            }
        }


    }

}