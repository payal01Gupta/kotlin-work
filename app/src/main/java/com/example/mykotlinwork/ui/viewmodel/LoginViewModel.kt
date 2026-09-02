package com.example.mykotlinwork.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykotlinwork.network.RetrofitClient.RetrofitClient
import com.example.mykotlinwork.ui.models.LoginResponse
import com.example.mykotlinwork.ui.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository : LoginRepository = LoginRepository(RetrofitClient.apiService)
    private val _loginResponse = MutableLiveData<LoginResponse>()

    private val loginResponse : LiveData<LoginResponse> = _loginResponse

//    private val _liveCategories : MutableLiveData<List<Category>>()
//    private val liveCategories : LiveData<List<Category>> = _liveCategories

    suspend fun login(username: String, password: String){
        viewModelScope.launch {
            val result = repository.login(username, password)
            result.onSuccess {
                _loginResponse.value = it
            }
            result.onFailure {
                _loginResponse.value = null
            }
        }
    }
}