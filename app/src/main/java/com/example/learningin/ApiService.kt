package com.example.learningin

import retrofit2.Call
import com.example.learningin.data.QuizResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String)

interface ApiService {
    @GET("api.php?amount=10")
    suspend fun getQuizQuestions(): QuizResponse

    @POST("api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}