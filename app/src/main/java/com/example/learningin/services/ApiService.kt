package com.example.learningin.services

import retrofit2.Call
import com.example.learningin.data.QuizResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String)

interface ApiService {
    @GET("api.php")
    fun getQuizQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("type") type: String
    ): Call<QuizResponse>

    @POST("api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}