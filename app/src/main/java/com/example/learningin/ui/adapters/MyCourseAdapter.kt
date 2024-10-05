package com.example.learningin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learningin.R
import com.example.learningin.data.Course

class MyCourseAdapter (
    private val mycourses: List<Course>,
    private val itemClick: (Course) -> Unit
) : RecyclerView.Adapter<MyCourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = mycourses[position]
        holder.bind(course, itemClick)
    }
    override fun getItemCount(): Int = mycourses.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.courseTitle)
        private val overviewTextView: TextView = itemView.findViewById(R.id.courseDesc)
        private val imageView: ImageView = itemView.findViewById(R.id.iconBook)

        fun bind(course: Course, clickListener: (Course) -> Unit) {
            titleTextView.text = course.title
            overviewTextView.text = course.overview
            imageView.setImageResource(course.image)
            itemView.setOnClickListener { clickListener(course) }
        }
    }
}
