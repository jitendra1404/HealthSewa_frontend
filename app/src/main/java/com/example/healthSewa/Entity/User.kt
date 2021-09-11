package com.example.healthSewa.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class User(
    @PrimaryKey
        val _id: String="",
        var custo_name :String? =null,
        var custo_address : String?=null,
        var custo_mobile: String?=null,
        var gender:String?=null,
         var custo_password:String?=null

)
