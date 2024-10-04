//package com.example.learningin.ui.fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.RecyclerView
//import com.example.learningin.ui.adapters.OptionsAdapter
//import com.example.learningin.viewmodel.QuizViewModel
//import com.example.learningin.R
//import com.example.learningin.data.QuizQuestion
//
//class QuizFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_quiz, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        questionTextView = view.findViewById(R.id.question_text_view)
//        optionsRecyclerView = view.findViewById(R.id.options_recycler_view)
//        adapter = OptionsAdapter { selectedAnswer -> handleAnswer(selectedAnswer) }
//        optionsRecyclerView.adapter = adapter
//
//        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
//        viewModel.fetchQuizQuestions()
//        viewModel.questions.observe(viewLifecycleOwner) { questions ->
//            quizQuestions = questions
//            displayQuestion()
//        }
//    }
//
//    private fun displayQuestion() {
//        if (currentQuestionIndex < quizQuestions.size) {
//            val question = quizQuestions[currentQuestionIndex]
//            questionTextView.text = question.question
//            val options = (question.incorrect_answers + question.correct_answer).shuffled()
//            adapter.submitList(options)
//        } else {
//            // Handle quiz completion
//        }
//    }
//
//    private fun handleAnswer(selectedAnswer: String) {
//        val correctAnswer = quizQuestions[currentQuestionIndex].correct_answer
//        // Provide feedback on the answer
//        if (selectedAnswer == correctAnswer) {
//            // Correct answer handling
//        } else {
//            // Incorrect answer handling
//        }
//        currentQuestionIndex++
//        displayQuestion()
//    }
//}