package com.example.healthSewa.dao

import androidx.room.*

import com.example.healthSewa.Entity.Review

@Dao
interface ReviewDAO {

    @Insert
    suspend fun insertReview(review: Review)

    @Query("SELECT * FROM Review")
    suspend fun getAllReview():MutableList<Review>

    @Delete
    suspend fun deleteReview(review: Review)

}