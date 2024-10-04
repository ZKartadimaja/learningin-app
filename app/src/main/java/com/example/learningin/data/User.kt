package com.example.learningin.data

data class User(
    val fullName: String,
    val email: String,
    val enrolledCourses: List<Course>
)