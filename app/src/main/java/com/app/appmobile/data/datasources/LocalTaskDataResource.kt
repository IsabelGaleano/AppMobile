package com.app.appmobile.data.datasources

import com.app.appmobile.domain.models.TaskModel
import com.app.appmobile.models.Task
import com.app.appmobile.models.TaskResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface LocalTaskDataResource {

    @Headers("Content-Type: application/json")
    @GET("tasks")
    fun getAllTasks() : Call<List<TaskModel>>

    @Headers("Content-Type: application/json")
    @POST("tasks")
    fun postTask(@Body taskData: TaskModel) : Call<TaskModel>

    @Headers("Content-Type: application/json")
    @PUT("tasks")
    fun putTask(@Body taskData: TaskModel) : Call<TaskModel>
}