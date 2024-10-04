//package com.example.learningin.ui.fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import com.example.learningin.R
//import com.example.learningin.data.Course
//
//class ClassOverviewFragment : Fragment() {
//    private lateinit var course: Course
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        // Safely retrieve the Parcelable Course argument
//        course = arguments?.getParcelable(ARG_COURSE) ?: throw IllegalArgumentException("Course is required")
//
//        // Set up views with course information
//        setupViews(view)
//    }
//
//    private fun setupViews(view: View) {
//        val courseTitleTextView: TextView = view.findViewById(R.id.text_view_course_title)
//        val courseOverviewTextView: TextView = view.findViewById(R.id.text_view_course_overview)
//
//        // Set the text for the views
//        courseTitleTextView.text = course.title
//        courseOverviewTextView.text = course.overview
//    }
//
//    companion object {
//        private const val ARG_COURSE = "course"
//
//        fun newInstance(course: Course) = ClassOverviewFragment().apply {
//            arguments = Bundle().apply {
//                putParcelable(ARG_COURSE, course)
//            }
//        }
//    }
//}
//
//private fun Bundle.putParcelable(argCourse: String, course: Course) {
//
//}
