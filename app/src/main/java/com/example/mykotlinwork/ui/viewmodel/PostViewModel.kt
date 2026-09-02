package com.example.mykotlinwork.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykotlinwork.models.PostModel
import com.example.mykotlinwork.network.RetrofitClient.RetrofitClient
import com.example.mykotlinwork.sealedClasses.UiState
import com.example.mykotlinwork.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
     private val repository = PostRepository(RetrofitClient.apiService)
     private val _posts = MutableLiveData<UiState<List<PostModel>>>()
     val posts : LiveData<UiState<List<PostModel>>> = _posts

    fun getPosts() {
        viewModelScope.launch {
            try {
                _posts.value = UiState.isLoading
                val result = repository.getPosts()
                result.onSuccess {
                    _posts.value = UiState.Success(it)
                }
                result.onFailure {
                    _posts.value = UiState.Error(it.message ?: "API Failure")
                }
            } catch (e:Exception) {
                _posts.value = UiState.Error(e.message ?: "API Failure")
            }
        }
    }
}