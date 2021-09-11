package com.example.healthSewa.response


import com.example.healthSewa.Entity.Review

data class AddReviewResponse (
    val success:Boolean?=null,
    val data: Review?=null
        )
