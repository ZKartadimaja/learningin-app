package com.example.learningin.data

data class QuizResponse(
    val response_code: Int,
    val results: List<QuizQuestion>
)