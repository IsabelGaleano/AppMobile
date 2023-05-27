package com.app.appmobile.api.Task

import com.app.appmobile.models.Task
import com.app.appmobile.models.TaskResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface TaskService {

    @Headers("Content-Type: application/json")
    @GET("tasks")
    fun getTasks() : Call<TaskResponse>
}