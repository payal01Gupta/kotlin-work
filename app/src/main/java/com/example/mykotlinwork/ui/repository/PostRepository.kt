package com.example.mykotlinwork.repository

import android.util.Log
import com.example.mykotlinwork.models.PostModel
import com.example.mykotlinwork.network.ApiService.ApiService
import kotlin.collections.emptyList

class PostRepository(private val apiService: ApiService) {

    suspend fun getPosts() : Result<List<PostModel>> {
        return try {
            val response = apiService.getPosts()
            if(response.isSuccessful) {
     //           Result.success(response.body() ?: emptyList())
                Result.success(response.body()?.let {
                    Log.e("test",it.toString())
                    it
                } ?:run {
                    emptyList()
                })
            } else{
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}