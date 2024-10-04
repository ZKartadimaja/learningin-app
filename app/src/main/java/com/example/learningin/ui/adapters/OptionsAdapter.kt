package com.example.learningin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learningin.R

class OptionsAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>() {

    private var options: List<String> = emptyList()

    class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionText: TextView = itemView.findViewById(R.id.option_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_option, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionText.text = options[position]
        holder.itemView.setOnClickListener { onClick(options[position]) }
    }

    override fun getItemCount(): Int = options.size

    fun submitList(newOptions: List<String>) {
        options = newOptions
        notifyDataSetChanged()
    }
}