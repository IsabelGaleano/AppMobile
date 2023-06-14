package com.app.appmobile.data.repositories

import android.util.Log
import com.app.appmobile.api.ServiceBuilder
import com.app.appmobile.api.Task.TaskService
import com.app.appmobile.data.datasources.LocalTaskDataResource
import com.app.appmobile.domain.models.TaskModel
import com.app.appmobile.domain.repositories.TaskRepository
import com.app.appmobile.models.Task
import com.app.appmobile.models.TaskResponse
import retrofit2.Call
import retrofit2.Response


class TaskRepositoryImpl(): TaskRepository {

   override fun getAllNotess(callback: (List<TaskModel>?) -> Unit) {
        val serviceBuilder = ServiceBuilder.buildService(LocalTaskDataResource::class.java)
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


}