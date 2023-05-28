package com.app.appmobile.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.adapters.TaskAdapter
import com.app.appmobile.api.ServiceBuilder
import com.app.appmobile.api.Task.TaskService
import com.app.appmobile.models.Task
import com.app.appmobile.models.TaskResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val newTask = findViewById<EditText>(R.id.etNewTask)
        val addTask = findViewById<Button>(R.id.buttonAddTask)

        val serviceBuilder = ServiceBuilder.buildService(TaskService::class.java)
        val call = serviceBuilder.getTasks()


        val button = findViewById<Button>(R.id.buttonGetRequest)
        button.setOnClickListener {
            call.enqueue(object : retrofit2.Callback<TaskResponse> {
                override fun onResponse(
                    call: Call<TaskResponse>,
                    response: Response<TaskResponse>
                ) {
                    if (response.isSuccessful) {
                        val taskResponse = response.body()
                        val taskList = taskResponse?.tasks
                        if (taskList != null) {
                            initRecyclerView(taskList)
                        }
                        println(response.body().toString())
                        Log.e("success", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                    t.printStackTrace()
                    println(t.message.toString())
                    Log.e("error", t.message.toString())
                }
            })
        }

        addTask.setOnClickListener {
            registerTask(newTask.text.toString())
        }


    }


    private fun registerTask(textTask: String) {
        val task = Task("","123675", textTask, false)

        val gson = Gson()
        val json = gson.toJson(task)
        val taskData = gson.fromJson(json, Task::class.java)

        val serviceBuilder = ServiceBuilder.buildService(TaskService::class.java)
        val call = serviceBuilder.postTask(taskData)

        call.enqueue(object : retrofit2.Callback<Task> {

            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                if (response.isSuccessful) {
                    println(response.body().toString())
                    Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Task>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
                Log.e("error", t.message.toString())
            }
        })
    }
    private fun initRecyclerView(taskList: List<Task>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTask)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TaskAdapter(taskList)
    }
}