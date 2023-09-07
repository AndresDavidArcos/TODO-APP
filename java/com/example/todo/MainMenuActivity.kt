package com.example.todo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainMenuActivity : AppCompatActivity() {
    private val categories = listOf(TaskCategory.Business
    ,TaskCategory.Personal, TaskCategory.Other)

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var btnAddTask: FloatingActionButton
    private lateinit var dialog: Dialog
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var rvTask: RecyclerView
    private  var previousSelectedCategory: TaskCategory? = null
    private var tasks = LinkedHashMap<Int, Task>()

    fun initVars(){
        rvCategories = findViewById(R.id.rvCategories)
        rvTask = findViewById(R.id.rvTasks)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        initVars()
        categories()
        addTaskBtn()
        dialog()
        tasks()
    }

    private fun categorySelected(position:Int) {
        val categorie = categories[position]

        categorie.isSelected = !categorie.isSelected

        if(previousSelectedCategory == null){
            filterTasksByCategory(categorie)
            previousSelectedCategory = categorie
        }else{
            if(categorie.isSelected){
                previousSelectedCategory!!.isSelected = !categorie.isSelected
                filterTasksByCategory(categorie)
                previousSelectedCategory = categorie
            }else{
                previousSelectedCategory = null
                showAllTasks()
            }

        }


        categoriesAdapter.notifyDataSetChanged()
    }

    private fun showAllTasks() {
        tasksAdapter.tasks = tasks
        tasksAdapter.notifyDataSetChanged()
    }

    private fun filterTasksByCategory(categorie: TaskCategory) {
        val filteredTasks = tasks.filterValues { task ->
            task.category.equals(categorie)
        } as LinkedHashMap<Int, Task>

        tasksAdapter.tasks = filteredTasks
        tasksAdapter.notifyDataSetChanged()
    }


    fun categories(){
        categoriesAdapter = CategoriesAdapter(categories) { position -> categorySelected(position) }
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    fun tasks(){
        tasksAdapter = TasksAdapter(tasks) { id -> taskChecked(id) }
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = tasksAdapter
    }

    private fun taskChecked(id: Int) {
        val task = tasks[id]
        task!!.isChecked = !task.isChecked
        Log.i("TASKSCHECKED", "la task $id tiene el checked en ${task.isChecked}")
        tasksAdapter.notifyDataSetChanged()
    }

    fun addTaskBtn(){
        btnAddTask = findViewById(R.id.btnAddTask)
        btnAddTask.setOnClickListener {
            dialog.show()
        }
    }

    fun dialog(){
        dialog = Dialog(this)
        dialog.setContentView(R.layout.add_task_dialog)
        val editText = dialog.findViewById<EditText>(R.id.dialogEditText)
        val radioGroup = dialog.findViewById<RadioGroup>(R.id.dialogRadioGroup)
        val btnAddTask = dialog.findViewById<Button>(R.id.dialogBtnAddTask)
        btnAddTask.setOnClickListener{
            val taskCat = TaskCategory.identifyCategory(dialog.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString())
            val taskDesc = editText.text.toString()
            tasks.put(Task.idCounter,(Task(taskDesc, taskCat, false)))
            tasksAdapter.notifyDataSetChanged()
            editText.text.clear()
            dialog.hide()
        }

        dialog.hide()
    }

}