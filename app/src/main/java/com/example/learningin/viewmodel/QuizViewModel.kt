package com.example.learningin.viewmodel

import com.example.learningin.services.QuizApiService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.learningin.data.QuizQuestion
import com.example.learningin.data.QuizResponse

class QuizViewModel : ViewModel() {
    private val _questions = MutableLiveData<List<QuizQuestion>>()
    val questions: LiveData<List<QuizQuestion>> get() = _questions

    private val _currentQuestionIndex = MutableLiveData<Int>()
    val currentQuestionIndex: LiveData<Int> get() = _currentQuestionIndex

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    init {
        _currentQuestionIndex.value = 0
        _score.value = 0
    }

    fun fetchQuizQuestions() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quizApiService = retrofit.create(QuizApiService::class.java)

        quizApiService.getQuizQuestions(amount = 3, category = 9, type = "boolean")
            .enqueue(object : Callback<QuizResponse> {
                override fun onResponse(call: Call<QuizResponse>, response: Response<QuizResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        _questions.value = response.body()!!.results
                    }
                }

                override fun onFailure(call: Call<QuizResponse>, t: Throwable) {
                    // Handle failure
                }
            })
    }

    fun moveToNextQuestion() {
        _currentQuestionIndex.value = (_currentQuestionIndex.value ?: 0) + 1
    }

    fun incrementScore() {
        _score.value = (_score.value ?: 0) + 1
    }
}
