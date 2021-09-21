package com.example.healthSewa.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.healthSewa.Entity.Review
import com.example.healthSewa.R
import com.example.healthSewa.Repository.ReviewRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ReviewAdapter (
    private val context: Context,
    private val lstReview:MutableList<Review>
): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //        val profile: ImageView
        val report_title: TextView
        val report_description: TextView
        val specialist_name: TextView
        val delete: ImageButton

        init {

            report_title = view.findViewById(R.id.tvReportTitle)
            report_description= view.findViewById(R.id.tvReportDescription)
            specialist_name= view.findViewById(R.id.tvSpecialistName)
            delete = view.findViewById(R.id.btnDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_review_layout, parent, false)
        return ReviewAdapter.ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = lstReview[position]
        holder.report_title.text = review.Report_title
        holder.report_description.text = review.Report_description
        holder.specialist_name.text = review.Specialist_name


        holder.delete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete ${review.Report_title}")
            builder.setMessage("Are you sure do you want to delete ${review.Report_title} ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                deleteReview(review)
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun deleteReview(review: Review) {
        CoroutineScope(Dispatchers.IO).launch {
//            ReviewDB.getInstance(context).getReviewDAO().deleteReview(review)
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "${review.Report_title} deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            try {
                val reviewRepository = ReviewRepository()
                val response = reviewRepository.deleteReview(review._id!!)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
//                        Toast.makeText(context, "${donor.fullname} deleted successfully", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//                        withContext(Dispatchers.Main) {
                        lstReview.remove(review)
                        notifyDataSetChanged()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return lstReview.size
    }
}

