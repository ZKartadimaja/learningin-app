package com.example.learningin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learningin.R
import com.example.learningin.RetrofitClient
import com.example.learningin.data.User
import com.example.learningin.data.UserResponse
import com.example.learningin.services.LoginRequest
import com.example.learningin.services.LoginResponse
import com.example.learningin.ui.fragments.ProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginBtn)

        loginButton.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val loginRequest = LoginRequest(email, password)


        RetrofitClient.apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Retrieve user ID from login response (if available)
                    val userId = 4 // Use a static ID or another method to retrieve ID based on your app's logic
                    fetchUserInfo(userId)
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchUserInfo(userId: Int) {
        RetrofitClient.apiService.getUser(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userData = response.body()?.data
                    userData?.let {
                        // Assuming the API returns first_name and last_name
                        val fullName = "${it.first_name} ${it.last_name}"
                        val email = it.email ?: "No Email Provided"

                        val userObj = User(
                            fullName = fullName,
                            email = email,
                            enrolledCourses = emptyList() // Adjust as needed
                        )

                        navigateToHomeFragment(userObj) // Pass the user object
                    } ?: run {
                        Toast.makeText(this@LoginActivity, "User data not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Failed to fetch user data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToHomeFragment(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("user_data", user)
        }
        startActivity(intent)
        finish() // Close the login activity
    }

    private fun navigateToProfileFragment(user: User) {
        val profileFragment = ProfileFragment().apply {
            arguments = Bundle().apply {
                putParcelable("user_data", user) // Pass the user data
            }
        }

        // Use a FragmentTransaction to replace the current fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, profileFragment) // Make sure you have the correct container ID
            .commit()
    }
}