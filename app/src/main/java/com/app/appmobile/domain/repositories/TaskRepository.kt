package com.app.appmobile.domain.repositories

import com.app.appmobile.domain.models.TaskModel

interface TaskRepository {

    fun getAllNotes(callback: (List<TaskModel>?) -> Unit)

    fun addTask(taskModel: TaskModel?, callback: (TaskModel?) -> Unit)

    fun updateTask(taskModel: TaskModel?, callback: (TaskModel?) -> Unit)



}