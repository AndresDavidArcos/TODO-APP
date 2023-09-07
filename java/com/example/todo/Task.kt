package com.example.todo

class Task(var desc: String, var category: TaskCategory, var isChecked: Boolean) {
    var taskId: Int

    init {
        taskId = idCounter
        idCounter++
    }
    companion object {
        var idCounter = 0
    }
}