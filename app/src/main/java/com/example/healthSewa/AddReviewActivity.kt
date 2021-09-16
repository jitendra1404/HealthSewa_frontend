package com.example.healthSewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthSewa.Entity.Review
import com.example.healthSewa.Repository.ReviewRepository
import com.example.healthSewa.db.ReviewDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddReviewActivity : AppCompatActivity() {

    private lateinit var etReportTitle: EditText
    private lateinit var etReportDescription: EditText
    private lateinit var etSpecialistName: EditText
    private lateinit var btnAddReview: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        etReportTitle=findViewById(R.id.etreporttile)
        etReportDescription=findViewById(R.id.etreportdescription)
        etSpecialistName=findViewById(R.id.etSpecialistName)
        btnAddReview=findViewById(R.id.btnAddReview)

        btnAddReview.setOnClickListener {
            addReview()
        }
    }

    private fun addReview() {
        val reporttitle = etReportTitle.text.toString()
        val reportdescription = etReportDescription.text.toString()
        val specialistname = etSpecialistName.text.toString()

        val review = Review(
            Report_title =   reporttitle,
            Report_description = reportdescription,
            Specialist_name = specialistname
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {

                ReviewDB
                    .getInstance(this@AddReviewActivity)
                    .getReviewDAO()
                    .insertReview(review)

                val reviewRepository = ReviewRepository()
                val response = reviewRepository.addReview(review)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddReviewActivity,
                            "New Review Added Successfully",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddReviewActivity,
                        "Error ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        }

    }
}