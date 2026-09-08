package com.example.mykotlinwork.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykotlinwork.network.RetrofitClient.RetrofitClient
import com.example.mykotlinwork.ui.models.Category
import com.example.mykotlinwork.ui.models.LoginResponse
import com.example.mykotlinwork.ui.repository.LoginRepository
import kotlinx.coroutines.launch
import java.util.Locale

class LoginViewModel : ViewModel() {
    private val repository : LoginRepository = LoginRepository(RetrofitClient.apiService)
    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse : LiveData<LoginResponse> = _loginResponse

    private val _liveCategories = MutableLiveData<List<Category>>()
    val liveCategories : LiveData<List<Category>> = _liveCategories

//    private val _liveCategories : MutableLiveData<List<Category>>()
//    private val liveCategories : LiveData<List<Category>> = _liveCategories

    fun login(username: String, password: String) {

        repository.login(
            username = username,
            password = password,
            onSuccess = { response ->
                // FIRST API SUCCESS
                _loginResponse.value = response
                // Now call SECOND API
                liveCategories(username, password) },

            onError = { errorMessage ->
                println("Login Error: ${errorMessage}")
            }
        )
    }


     fun liveCategories(username: String, password: String) {
        repository.getLiveCategories(
            username = username,
            password = password,
            onSuccess = { categoryList ->
                // SECOND API SUCCESS
                _liveCategories.value = categoryList },

            onError = { errorMessage ->
                println("Category Error: ${errorMessage}")
            }
        )
    }

     /*fun login(username: String, password: String){
        viewModelScope.launch {
            val result = repository.login(username, password)
            result.onSuccess {
                _loginResponse.value = it
            }
            result.onFailure {
                _loginResponse.value = null
                println("Login Error: ${it.message}")
            }
        }
    }

    fun liveCategories(username: String, password: String){
        viewModelScope.launch {
            val result = repository.getLiveCategories(username, password)
            result.onSuccess {
                _liveCategories.value = it
            }
            result.onFailure {
                _liveCategories.value = null
                println("Category Error: ${it.message}")
            }
        }
    }*/
}