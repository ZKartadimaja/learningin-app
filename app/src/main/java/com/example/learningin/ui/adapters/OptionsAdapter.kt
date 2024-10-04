package com.example.learningin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learningin.R
import com.example.learningin.data.QuizQuestion

class QuestionsAdapter(
    private val questions: List<QuizQuestion>,
    private val onAnswerSelected: (Int, String) -> Unit
) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    private val selectedAnswers = mutableMapOf<Int, String>()

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.question_text)
        val optionsGroup: RadioGroup = itemView.findViewById(R.id.options_group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_option, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.questionTextView.text = question.question

        holder.optionsGroup.removeAllViews()
        val options = (question.incorrect_answers + question.correct_answer).shuffled()
        options.forEach { option ->
            val radioButton = RadioButton(holder.itemView.context).apply {
                text = option
                id = View.generateViewId()
            }
            holder.optionsGroup.addView(radioButton)

            holder.optionsGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedOption = group.findViewById<RadioButton>(checkedId)
                selectedAnswers[position] = selectedOption.text.toString()
                onAnswerSelected(position, selectedOption.text.toString())
            }
        }
    }

    override fun getItemCount(): Int = questions.size

    fun getSelectedAnswers(): Map<Int, String> = selectedAnswers
}
