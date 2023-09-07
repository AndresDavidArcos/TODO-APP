package com.example.todo

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TasksViewHolder (view: View) : RecyclerView.ViewHolder(view){
    private val tvTask:TextView = view.findViewById(R.id.taskTextView)
    private val cbTask:CheckBox = view.findViewById(R.id.taskCheckBox)

    fun render(task: Task?){
        tvTask.text = task!!.desc
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, task.category.dividerColorId)
        )
        cbTask.isChecked = task.isChecked

        if(task.isChecked){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags = tvTask.paintFlags  and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
 }

}
