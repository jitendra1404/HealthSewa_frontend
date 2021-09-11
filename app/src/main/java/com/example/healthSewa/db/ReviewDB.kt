package com.example.healthSewa.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthSewa.Entity.Review
import com.example.healthSewa.dao.ReviewDAO

@Database(
    entities = [(Review::class)],
    version = 3,
    exportSchema = false
)

abstract class ReviewDB: RoomDatabase(){
    abstract fun getReviewDAO(): ReviewDAO

    companion object{
        @Volatile
        private var instance : ReviewDB? = null
        fun getInstance(context: Context): ReviewDB{
            if (instance==null){
                synchronized(ReviewDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ReviewDB::class.java,
                "DonorDB"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}
