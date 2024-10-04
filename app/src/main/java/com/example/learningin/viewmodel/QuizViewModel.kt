package com.example.learningin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningin.RetrofitClient
import com.example.learningin.data.QuizQuestion
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private val _questions = MutableLiveData<List<QuizQuestion>>()
    val questions: LiveData<List<QuizQuestion>> get() = _questions

    fun fetchQuizQuestions() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getQuizQuestions()
                _questions.postValue(response.results)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}