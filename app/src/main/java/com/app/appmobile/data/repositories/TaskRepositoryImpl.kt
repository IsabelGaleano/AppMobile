package com.app.appmobile.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.appmobile.api.Task.TaskService
import com.app.appmobile.data.ServiceBuilder
import com.app.appmobile.data.datasources.LocalTaskDataResource
import com.app.appmobile.domain.models.TaskModel
import com.app.appmobile.domain.repositories.TaskRepository
import com.app.appmobile.models.Task
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response


class TaskRepositoryImpl(): TaskRepository {

    private val serviceBuilder = ServiceBuilder.buildService(LocalTaskDataResource::class.java)


   override fun getAllNotes(callback: (List<TaskModel>?) -> Unit) {
        val call = serviceBuilder.getAllTasks()

        call.enqueue(object : retrofit2.Callback<List<TaskModel>> {
            override fun onResponse(
                call: Call<List<TaskModel>>,
                response: Response<List<TaskModel>>
            ) {
                if (response.isSuccessful) {
                    val taskResponse = response.body()
                    val taskList = taskResponse
                    callback(taskList)
                    println(response.body().toString())
                    Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<List<TaskModel>>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
                Log.e("error", t.message.toString())
                callback(null)
            }
        })
    }

    override fun addTask(taskModel: TaskModel?, callback: (TaskModel?) -> Unit) {
        val gson = Gson()
        val json = gson.toJson(taskModel)
        val taskData = gson.fromJson(json, TaskModel::class.java)

        val call = serviceBuilder.postTask(taskData)

        call.enqueue(object : retrofit2.Callback<TaskModel> {
            override fun onResponse(call: Call<TaskModel>, response: Response<TaskModel>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val jsonBody = gson.toJson(responseBody)
                    val newTask = gson.fromJson(jsonBody, TaskModel::class.java)
                    callback(newTask)
                    Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<TaskModel>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
                Log.e("error", t.message.toString())
            }
        })
    }

    override fun updateTask(taskModel: TaskModel?, callback: (TaskModel?) -> Unit) {
        val gson = Gson()
        val json = gson.toJson(taskModel)
        val taskData = gson.fromJson(json, TaskModel::class.java)

        val call = serviceBuilder.putTask(taskData)

        call.enqueue(object : retrofit2.Callback<TaskModel> {
            override fun onResponse(call: Call<TaskModel>, response: Response<TaskModel>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val jsonBody = gson.toJson(responseBody)
                    val updateTask = gson.fromJson(jsonBody, TaskModel::class.java)
                    callback(updateTask)
                    Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<TaskModel>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
                Log.e("error", t.message.toString())
            }
        })
    }


}