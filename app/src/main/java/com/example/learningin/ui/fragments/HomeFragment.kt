package com.example.learningin.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningin.R
import com.example.learningin.activity.CourseActivity
import com.example.learningin.data.Course
import com.example.learningin.ui.adapters.CourseAdapter


class HomeFragment : Fragment() {

    private lateinit var courseList: RecyclerView
    private lateinit var adapter: CourseAdapter
    private val courses: List<Course> = listOf() // Load courses from a database or API

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // Call super first

        courseList = view.findViewById(R.id.recyclerViewHistory)
        courseList.layoutManager = LinearLayoutManager(requireContext()) // Provide context

        // Create a data list and populate it
        val data = ArrayList<Course>()
        for (i in 1..5) {
            data.add(Course("Course Title $i", "Item $i", R.drawable.book))
        }

        // Use the data list for the adapter
        adapter = CourseAdapter(data) { course -> onCourseSelected(course) }
        courseList.adapter = adapter
    }

    private fun onCourseSelected(course: Course) {
        // Create an Intent to start CourseActivity
        val intent = Intent(requireContext(), CourseActivity::class.java)

        // Pass the course data to the CourseActivity (you can use Parcelable)
        intent.putExtra("courseTitle", course.title)
        intent.putExtra("courseDescription", course.overview)
        // Add any other data you want to pass

        // Start the CourseActivity
        startActivity(intent)
    }

}
