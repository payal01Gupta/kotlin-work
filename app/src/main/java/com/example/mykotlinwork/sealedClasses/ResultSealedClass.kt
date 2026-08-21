package com.example.mykotlinwork.sealedClasses
import kotlin.jvm.Throws

sealed class UiState<out T> {
    object isLoading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
