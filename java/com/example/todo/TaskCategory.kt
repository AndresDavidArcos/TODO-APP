package com.example.todo

sealed class TaskCategory (val dividerColorId: Int, public var isSelected: Boolean, val name: String){

    object Personal : TaskCategory(R.color.todo_personal_category, false, "Personal")

    object Business : TaskCategory(R.color.todo_business_category,false, "Negocios")

    object Other : TaskCategory(R.color.todo_other_category,false, "Otros")

    companion object {
        fun identifyCategory(category: String): TaskCategory {
            return when (category) {
                "Negocios" -> Business
                "Personal" -> Personal
                else -> Other
            }
        }
    }

}