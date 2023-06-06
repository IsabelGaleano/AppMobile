package com.app.appmobile.api.Task

import com.app.appmobile.models.Task
import com.app.appmobile.models.TaskResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface TaskService {

    @Headers("Content-Type: application/json")
    @GET("tasks")
    fun getTasks() : Call<TaskResponse>

    @Headers("Content-Type: application/json")
    @POST("tasks")
    fun postTask(@Body taskData: Task) : Call<Task>

    @Headers("Content-Type: application/json")
    @PUT("tasks")
    fun putTask(@Body taskData: Task) : Call<Task>

}