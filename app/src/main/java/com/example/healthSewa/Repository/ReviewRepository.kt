package com.example.healthSewa.Repository

import com.example.healthSewa.Entity.Review
import com.example.healthSewa.api.MyAPIRequest
import com.example.healthSewa.api.ServiceBuilder
import com.example.healthSewa.response.AddReviewResponse
import com.example.healthSewa.response.DeleteReviewResponse
import com.example.healthSewa.response.GetAllReviewResponse

class ReviewRepository:MyAPIRequest() {

    private val ReviewAPI =
        ServiceBuilder.buildServices(com.example.healthSewa.api.ReviewAPI::class.java)

      //Add Review
    suspend fun addReview(review: Review): AddReviewResponse {
        return apiRequest {
            ReviewAPI.addReview(
                ServiceBuilder.token!!, review
            )
        }
    }

//    //Delete Review

    suspend fun deleteReview(id: String): DeleteReviewResponse {
        return apiRequest {
            ReviewAPI.deleteReview(
                ServiceBuilder.token!!, id
            )
        }
    }

    // Get Review

    suspend fun getAllReview(): GetAllReviewResponse {
        return apiRequest {
            ReviewAPI.getAllReview(
                ServiceBuilder.token!!
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