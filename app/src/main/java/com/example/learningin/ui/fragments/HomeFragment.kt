package com.example.learningin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.learningin.R
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
        courseList = view.findViewById(R.id.recycler_view)
        adapter = CourseAdapter(courses) { course -> onCourseSelected(course) }
        courseList.adapter = adapter
    }

    private fun onCourseSelected(course: Course) {
        val fragment = ClassOverviewFragment.newInstance(course)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}