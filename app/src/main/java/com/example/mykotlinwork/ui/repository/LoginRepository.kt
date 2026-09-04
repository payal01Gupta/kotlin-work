package com.example.mykotlinwork.ui.repository

import com.example.mykotlinwork.network.ApiService.ApiService
import com.example.mykotlinwork.ui.models.Category
import com.example.mykotlinwork.ui.models.LoginResponse

class LoginRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = apiService.loginApi(username,password)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Response body is empty"))
            } else {
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getLiveCategories(username: String, password: String): Result<List<Category>> {
        return try {
            val response = apiService.getLiveCategories(username , password)
            if(response.isSuccessful){
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Response body is empty"))
            } else {
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}