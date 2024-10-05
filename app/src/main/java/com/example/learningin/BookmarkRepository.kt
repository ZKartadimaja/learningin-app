package com.example.learningin

import com.example.learningin.data.Course

object BookmarkRepository {
    private val bookmarkedCourses = mutableListOf<Course>()

    fun addBookmark(course: Course) {
        bookmarkedCourses.add(course)
    }

    fun getBookmarks(): List<Course> {
        return bookmarkedCourses
    }
}