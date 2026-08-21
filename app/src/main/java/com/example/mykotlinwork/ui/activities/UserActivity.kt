package com.example.mykotlinwork.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinwork.R
import com.example.mykotlinwork.adapter.PostAdapter
import com.example.mykotlinwork.adapter.UserAdapter
import com.example.mykotlinwork.sealedClasses.UiState
import com.example.mykotlinwork.viewModel.PostViewModel
import com.example.mykotlinwork.viewModel.UserViewModel
import kotlin.getValue

class UserActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewOne: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var adapter: UserAdapter
    private lateinit var adapterPost: PostAdapter

    private val viewModel: UserViewModel by viewModels()

    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerViewOne = findViewById(R.id.recyclerViewOne)
        progressBar = findViewById(R.id.progressBar)

        adapter = UserAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        observeUsers()
        viewModel.getUsers()

        adapterPost = PostAdapter()
        recyclerViewOne.layoutManager = LinearLayoutManager(this)
        recyclerViewOne.adapter = adapterPost
        observerPosts()
        postViewModel.getPosts()
    }

    private fun observeUsers() {
        viewModel.users.observe(this) { result ->
            when(result) {
                is UiState.isLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    val users = result.data
                    adapter.setUsers(users)
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, result.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observerPosts() {
        postViewModel.posts.observe(this) { posts ->
            when(posts){
                is UiState.isLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    val posts = posts.data
                    adapterPost.setPosts(posts)
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, posts.message,Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}