package com.example.mykotlinwork.ui.repository

import com.example.mykotlinwork.network.ApiService.ApiService

class LoginRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String) {
        apiService.loginApi(username, password)
    }
}