package com.example.learningin.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningin.R
import com.example.learningin.ui.adapters.QuestionsAdapter
import com.example.learningin.viewmodel.QuizViewModel

class QuizzizActivity : AppCompatActivity() {

    private lateinit var questionsRecyclerView: RecyclerView
    private lateinit var adapter: QuestionsAdapter
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizziz)

        questionsRecyclerView = findViewById(R.id.questions_recycler_view)
        questionsRecyclerView.layoutManager = LinearLayoutManager(this)

        submitButton = findViewById(R.id.submit_button)

        viewModel.fetchQuizQuestions()
        viewModel.questions.observe(this) { quizQuestions ->
            adapter = QuestionsAdapter(quizQuestions) { questionIndex, selectedAnswer ->
                handleAnswer(questionIndex, selectedAnswer)
            }
            questionsRecyclerView.adapter = adapter
        }
    }

    private fun handleAnswer(questionIndex: Int, selectedAnswer: String) {
        val correctAnswer = viewModel.questions.value?.get(questionIndex)?.correct_answer

        if (selectedAnswer == correctAnswer) {
//            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            viewModel.incrementScore()  // Increment score if correct
        } else {
//            Toast.makeText(this, "Incorrect! Correct answer: $correctAnswer", Toast.LENGTH_SHORT).show()
        }

        // Optionally, you can check if all questions have been answered and show total score
        if (adapter.getSelectedAnswers().size == viewModel.questions.value?.size) {
            val totalScore = viewModel.score.value ?: 0
            submitButton.setOnClickListener {
                Toast.makeText(this, "Quiz Complete! Your Score: $totalScore", Toast.LENGTH_SHORT).show()
            }
//            finish()  // or navigate to a results screen
        }
    }
}
