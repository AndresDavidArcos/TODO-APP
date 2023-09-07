package com.example.todo

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvCategoryName:TextView = view.findViewById(R.id.itemTaskCategoryText)
    private val divider:View = view.findViewById(R.id.itemTaskCategoryLine)
    private val card: CardView = view.findViewById(R.id.cardTaskCategory)
    fun render(taskCategory: TaskCategory){
        tvCategoryName.text = taskCategory.name
        divider.setBackgroundColor(
            ContextCompat.getColor(divider.context,taskCategory.dividerColorId)
        )

        Log.i("QUEPUTAS?", "${taskCategory.isSelected} con Id $layoutPosition")

        val color = if (taskCategory.isSelected) {
            R.color.todo_background_disabled
        }else{
            R.color.todo_background_card
        }

        card.setBackgroundColor(ContextCompat.getColor(card.context, color))

    }
}