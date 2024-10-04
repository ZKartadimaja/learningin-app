package com.example.learningin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.learningin.R

class MyCourseFragment : Fragment(){
    private lateinit var courseTitle: TextView
    private lateinit var courseDesc: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        courseTitle = view.findViewById(R.id.myCourseTitle)
        courseDesc = view.findViewById(R.id.myCourseDesc)

        // Get arguments from CourseActivity
        val args = arguments
        args?.let {
            val title = it.getString("courseTitle")
            val description = it.getString("courseDescription")
            courseTitle.text = title
            courseDesc.text = description
        }
    }

    companion object {
        fun newInstance(title: String, description: String): MyCourseFragment {
            val fragment = MyCourseFragment()
            val args = Bundle()
            args.putString("courseTitle", title)
            args.putString("courseDescription", description)
            fragment.arguments = args
            return fragment
        }
    }
}