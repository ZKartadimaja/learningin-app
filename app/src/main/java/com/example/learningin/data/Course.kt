package com.example.learningin.data

data class Course(
    val title: String,
    val overview: String,
    val videoUrl: String,
    val quizQuestions: List<QuizQuestion>
)