package com.example.mykotlinwork.ui.models

data class LoginResponse(
    val user_info: UserInfo,
    val server_info: ServerInfo,
    val error: String
)

data class UserInfo(
    val username: String,
    val password: String,
    val message: String,
    val auth: Int,
    val status: String,
    val exp_date: String,
    val is_trial: String,
    val active_cons: Int,
    val created_at: String,
    val max_connections: String,
    val allowed_output_formats: List<String>
)

data class ServerInfo(
    val xui: Boolean,
    val version: String,
    val revision: String?,
    val url: String,
    val port: String,
    val https_port: String,
    val server_protocol: String,
    val rtmp_port: String,
    val timestamp_now: Long,
    val time_now: String,
    val timezone: String
)