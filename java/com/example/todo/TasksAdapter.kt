package com.example.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter (public var tasks:LinkedHashMap<Int, Task>, private val taskChecked: (Int) -> Unit) : RecyclerView.Adapter<TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val taskId = tasks.keys.elementAt(position)
        val task = tasks[taskId]
        holder.render(task)
        holder.itemView.findViewById<CheckBox>(R.id.taskCheckBox).setOnClickListener {
            taskChecked(taskId)
        }
    }

}