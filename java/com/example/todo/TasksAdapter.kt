package com.example.todo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter (public var tasks:List<Task>, private val taskChecked: (Int) -> Unit) : RecyclerView.Adapter<TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        holder.itemView.findViewById<CheckBox>(R.id.taskCheckBox).setOnClickListener{
            taskChecked(position)
        }
    }

}