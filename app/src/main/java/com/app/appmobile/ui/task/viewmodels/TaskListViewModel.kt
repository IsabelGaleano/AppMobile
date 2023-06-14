package com.app.appmobile.ui.task.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.appmobile.data.repositories.TaskRepositoryImpl
import com.app.appmobile.domain.models.TaskModel
import com.app.appmobile.domain.repositories.TaskRepository

class TaskListViewModel : ViewModel() {

    private val repository = TaskRepositoryImpl()

    private val _taskListLiveData = MutableLiveData<List<TaskModel>>()
    val noteListLiveData: LiveData<List<TaskModel>>
        get() = _taskListLiveData

    fun onViewReady() {
        repository.getAllNotess { taskModels ->
            if (taskModels != null) {
                _taskListLiveData.value = taskModels

                println(taskModels.toString())
                Log.e("success", taskModels.toString())
            } else {
                // Manejar el caso de error
            }
        }
    }





}