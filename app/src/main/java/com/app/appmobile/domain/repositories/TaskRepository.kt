package com.app.appmobile.domain.repositories

import com.app.appmobile.domain.models.TaskModel

interface TaskRepository {

    fun getAllNotes(callback: (List<TaskModel>?) -> Unit)

    fun addNotes(callback: (TaskModel?) -> Unit): TaskModel? {
        val taskModel: TaskModel? = null
        return taskModel
    }



}