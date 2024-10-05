package com.example.learningin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.learningin.R
import com.example.learningin.data.User

class ProfileFragment : Fragment() {
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Retrieve user data from arguments
        arguments?.getParcelable<User>("user_data")?.let {
            user = it
            displayUserInfo()
        }
    }

    private fun displayUserInfo() {
        // Assuming you have TextViews to display the user's full name and email
        view?.findViewById<TextView>(R.id.fullNameTextView)?.text = user.fullName
        view?.findViewById<TextView>(R.id.emailTextView)?.text = user.email
    }
}