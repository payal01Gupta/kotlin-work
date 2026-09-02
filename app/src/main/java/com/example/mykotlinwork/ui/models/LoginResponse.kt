package com.example.mykotlinwork.ui.models

data class LoginResponse(val user_info: UserInfo,
                         val server_info: ServerInfo)

data class UserInfo(
    val username: String,
    val password: String,
    val status: String,
    val exp_date: String,
    val is_trial: String,
    val active_cons: String,
    val created_at: String
)

data class ServerInfo(
    val url: String,
    val port: String,
    val https_port: String
)
