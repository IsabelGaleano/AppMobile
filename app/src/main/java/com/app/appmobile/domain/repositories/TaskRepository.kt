package com.app.appmobile.domain.repositories

import com.app.appmobile.domain.models.TaskModel

interface TaskRepository {

    fun getAllNotess(callback: (List<TaskModel>?) -> Unit)

}