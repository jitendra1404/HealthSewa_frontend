package com.example.healthSewa

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.healthSewa.Entity.NotificationChannel
import com.example.healthSewa.Entity.User
import com.example.healthSewa.Repository.UserRepository
import com.example.healthSewa.api.ServiceBuilder
//import com.example.androidproject25b.db.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegistrationActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var address: EditText
    private lateinit var mobile: EditText
    private lateinit var gender: EditText
    private lateinit var confirmpassword: EditText
    private lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        username = findViewById(R.id.edRegisterUsername)
        gender = findViewById(R.id.edGender)
        mobile = findViewById(R.id.edmobile)
        address = findViewById(R.id.edAddress)
        password = findViewById(R.id.edPassword)
        confirmpassword = findViewById(R.id.edConfirmPassword)
        signUp = findViewById(R.id.btnSingUP)


        signUp.setOnClickListener {

            lowPriorityNotification()

            val username = username.text.toString()
            val address = address.text.toString()
            val mobile = mobile.text.toString()
            val gender = gender.text.toString()
            val Password = password.text.toString()
            val ConfirmPassword = confirmpassword.text.toString()

            if (Password != ConfirmPassword) {
                password.error = "password does not match"
                password.requestFocus()
                return@setOnClickListener

            } else {
                val user = User(
                    custo_name = username,
                    custo_address = address,
                    custo_mobile = mobile,
                    gender = gender,
                    custo_password = Password
                )
//                Toast.makeText(this, "AAYO", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {

                    try {
                        val repository = UserRepository()
                        val response = repository.registerUser(user)
                        if (response.success == true) {
                            ServiceBuilder.token = "Bearer " + response.token
                            withContext(Main) {
                                Toast.makeText(
                                    this@RegistrationActivity,
                                    "Successfully registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Main) {
                            Toast.makeText(
                                this@RegistrationActivity,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
            }
        }
    }

    private fun lowPriorityNotification() {

        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannels = NotificationChannel(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_2)
            .setSmallIcon(R.drawable.ic_baseline_message_24)
            .setContentTitle("Low priority notification")
            .setContentText("User Successfully Register")
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(2, notification)

    }
}



