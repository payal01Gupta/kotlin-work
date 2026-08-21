package com.example.mykotlinwork.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykotlinwork.models.UsersModel
import com.example.mykotlinwork.network.RetrofitClient.RetrofitClient
import com.example.mykotlinwork.repository.UserRepository
import com.example.mykotlinwork.sealedClasses.UiState
import kotlinx.coroutines.launch

class UserViewModel : ViewModel()  {
    private val repository = UserRepository(RetrofitClient.apiService)

    private val _users = MutableLiveData<UiState<List<UsersModel>>>()
    val users: LiveData<UiState<List<UsersModel>>> = _users

     fun getUsers() {
        viewModelScope.launch {
            try {
                _users.value = UiState.isLoading
                val response = repository.getUsers()
                _users.value = UiState.Success(response)

            } catch (e: Exception) {
                _users.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

  /*  sealed class UiState {
        object isLoading : UiState()
        data class Success(val users: List<UsersModel>) : UiState()
        data class Error(val message: String) : UiState()
    }*/
}