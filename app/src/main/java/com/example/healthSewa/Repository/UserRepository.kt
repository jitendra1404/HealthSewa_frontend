package com.example.healthSewa.Repository

import com.example.healthSewa.Entity.User
import com.example.healthSewa.api.MyAPIRequest
import com.example.healthSewa.api.ServiceBuilder
import com.example.healthSewa.api.UserAPI
import com.example.healthSewa.response.LoginResponse

class UserRepository:MyAPIRequest() {

    val userAPI = ServiceBuilder.buildServices(UserAPI::class.java)

    //Register User

    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }
    //login user

    suspend fun loginUser(username: String, password: String): LoginResponse {
        return apiRequest {
            userAPI.loginUser(username, password)
        }
    }
}