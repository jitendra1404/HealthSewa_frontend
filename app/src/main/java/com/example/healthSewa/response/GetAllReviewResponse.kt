package com.example.healthSewa.response


import com.example.healthSewa.Entity.Review

data class GetAllReviewResponse (
    val success:Boolean?=null,
    val count:Int?=null,
    val data :MutableList<Review>?=null
        )
