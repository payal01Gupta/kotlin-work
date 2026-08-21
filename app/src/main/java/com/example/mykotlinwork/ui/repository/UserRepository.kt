package com.example.mykotlinwork.repository

import com.example.mykotlinwork.models.UsersModel
import com.example.mykotlinwork.network.ApiService.ApiService

class UserRepository(private val apiService: ApiService) {

    suspend fun getUsers(): List<UsersModel> {
        return apiService.getUsers()
    }
}