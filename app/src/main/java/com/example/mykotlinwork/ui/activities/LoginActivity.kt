package com.example.mykotlinwork.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinwork.R
import com.example.mykotlinwork.ui.adapter.CategoryAdapter
import com.example.mykotlinwork.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var btnSubmit: Button
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView


    private val viewModel: LoginViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnSubmit = findViewById(R.id.btn_submit)
        recyclerView = findViewById(R.id.recyclerView)

        observeLoginResponse()
        btnSubmit.setOnClickListener {
            val username = username.text.toString()
            val password = password.text.toString()
            viewModel.login(username, password)
        }
        categoryAdapter = CategoryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = categoryAdapter
        observeLiveCategories()
    }

    private fun observeLiveCategories() {
        viewModel.liveCategories.observe(this) { categories ->
            if (categories != null) {
                categoryAdapter.updateList(categories)
            }
        }
    }

    private fun observeLoginResponse() {
        viewModel.loginResponse.observe(this) { loginResponse ->
            if (loginResponse != null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                val username = username.text.toString()
                val password = password.text.toString()
                viewModel.liveCategories(username,password)
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}