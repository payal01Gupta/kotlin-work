package com.example.mykotlinwork.ui.repository

import com.example.mykotlinwork.network.ApiService.ApiService
import com.example.mykotlinwork.ui.models.Category
import com.example.mykotlinwork.ui.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(private val apiService: ApiService) {

//    suspend fun login(username: String, password: String): Result<LoginResponse> {
//        return try {
//            val response = apiService.loginApi(username,password)
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    Result.success(it)
//                } ?: Result.failure(Exception("Response body is empty"))
//            } else {
//                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun getLiveCategories(username: String, password: String): Result<List<Category>> {
//        return try {
//            val response = apiService.getLiveCategories(username , password)
//            if(response.isSuccessful){
//                response.body()?.let {
//                    Result.success(it)
//                } ?: Result.failure(Exception("Response body is empty"))
//            } else {
//                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

    fun login(username: String,
        password: String,
        onSuccess: (LoginResponse) -> Unit,
        onError: (String) -> Unit) {

        apiService.loginApi(username, password)
            .enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            onSuccess(loginResponse)
                        } else {
                            onError("Login response is empty")
                        }
                    } else {
                        onError("Login failed: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onError(t.message ?: "Login API failed")
                }
            })
    }

    fun getLiveCategories(
        username: String,
        password: String,
        onSuccess: (List<Category>) -> Unit,
        onError: (String) -> Unit) {

        apiService.getLiveCategories(username = username, password = password)
            .enqueue(object : Callback<List<Category>> {
                override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                    if (response.isSuccessful) {
                        val categories = response.body()
                        if (categories != null) {
                            onSuccess(categories)
                        } else {
                            onError("Category data is empty")
                        }
                    } else {
                        onError("Category API failed: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    onError(t.message ?: "Category API failed")
                }
            })
    }
}