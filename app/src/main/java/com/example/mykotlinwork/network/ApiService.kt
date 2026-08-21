package com.example.mykotlinwork.network.ApiService

import com.example.mykotlinwork.models.PostModel
import com.example.mykotlinwork.models.UsersModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UsersModel>

    @GET("posts")
    suspend fun getPosts() : Response<List<PostModel>>
}