package com.example.learningin.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningin.BookmarkRepository
import com.example.learningin.R
import com.example.learningin.activity.CourseActivity
import com.example.learningin.data.Course
import com.example.learningin.ui.adapters.CourseAdapter

class MyCourseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewMyCourses)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Fetch the bookmarked courses
        val bookmarkedCourses = BookmarkRepository.getBookmarks()
        adapter = CourseAdapter(bookmarkedCourses) { course -> onCourseSelected(course) }
        recyclerView.adapter = adapter
    }

    private fun onCourseSelected(course: Course) {
        // Create an Intent to start CourseActivity
        val intent = Intent(requireContext(), CourseActivity::class.java).apply {
            putExtra("courseTitle", course.title)
            putExtra("courseDescription", course.overview)
            // Pass any other necessary data
        }

        // Start CourseActivity
        startActivity(intent)
    }
}