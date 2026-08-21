package com.example.mykotlinwork.models

data class PostModel (
    val userId : Int ?= null,
    val id: Int ?= null,
    val title: String ?= null,
    val body: String ?= null )