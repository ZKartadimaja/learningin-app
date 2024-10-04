package com.example.learningin.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learningin.R
import com.example.learningin.ui.fragments.MyCourseFragment

class CourseActivity : AppCompatActivity() {

    private lateinit var courseTitle: TextView
    private lateinit var courseDescTitle: TextView
    private lateinit var courseDesc: TextView
    private lateinit var videoView: VideoView
    private lateinit var quizButton: Button
    private lateinit var bookmarkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        // Initialize views
        courseTitle = findViewById(R.id.courseTitle)
        courseDescTitle = findViewById(R.id.courseDescTitle)
        courseDesc = findViewById(R.id.courseDesc)
        videoView = findViewById(R.id.videoView)
        quizButton = findViewById(R.id.quizBtn)
        bookmarkButton = findViewById(R.id.bookmark)

        // Receive data from intent
        val title = intent.getStringExtra("courseTitle")
        val overview = intent.getStringExtra("courseDescription")

        courseTitle.text = title
        courseDesc.text = overview

        // Set MediaController for video playback controls
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Set video path
        val videoPath = "android.resource://${packageName}/${R.raw.sample_video}"
        videoView.setVideoURI(Uri.parse(videoPath))

//        videoView.setVideoURI("https://www.youtube.com/watch?v=pn7gC0wzsqU")

        // Set up button listeners
        quizButton.isEnabled = false // Initially disable the quiz button
        quizButton.setOnClickListener {
            // Navigate to Quiz Activity
            val intent = Intent(this, QuizzizActivity::class.java)
            startActivity(intent)
        }

        bookmarkButton.setOnClickListener {
            // Handle bookmarking the course
            Toast.makeText(this, "Course bookmarked!", Toast.LENGTH_SHORT).show()

            // Create and display MyCourseFragment
            val title = courseTitle.text.toString()
            val description = courseDesc.text.toString()

            val myCourseFragment = MyCourseFragment.newInstance(title, description)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, myCourseFragment) // Make sure you have a FrameLayout or similar with this ID in your activity layout
                .addToBackStack(null) // Optional: Adds this transaction to the back stack
                .commit()
        }

        // Enable quiz button when video finishes
        videoView.setOnCompletionListener {
            quizButton.isEnabled = true // Enable the quiz button
            Toast.makeText(this, "Video finished. You can now take the quiz!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        // Pause video playback when the activity is paused
        if (videoView.isPlaying) {
            videoView.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        // Resume video playback when the activity is resumed
        if (!videoView.isPlaying) {
            videoView.start()
        }
    }
}