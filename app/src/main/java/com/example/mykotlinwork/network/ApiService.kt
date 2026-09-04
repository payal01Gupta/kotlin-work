package com.example.mykotlinwork.network.ApiService

import com.example.mykotlinwork.models.PostModel
import com.example.mykotlinwork.models.UsersModel
import com.example.mykotlinwork.ui.models.Category
import com.example.mykotlinwork.ui.models.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UsersModel>

    @GET("posts")
    suspend fun getPosts() : Response<List<PostModel>>

    @GET("player_api.php?")
    suspend fun loginApi(@Query("username") username: String,
                         @Query("password") password: String): Response<LoginResponse>

    suspend fun getLiveCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_live_categories"
    ): Response<List<Category>>
}