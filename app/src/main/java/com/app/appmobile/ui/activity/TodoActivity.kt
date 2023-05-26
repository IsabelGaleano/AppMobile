package com.app.appmobile.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.adapters.TaskAdapter
import com.app.appmobile.models.Task
import com.app.appmobile.providers.TaskProvider

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initRecyclerView()
    }

    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTask)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TaskAdapter(TaskProvider.taskList)
    }
}