package com.app.appmobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.models.Task

class TaskAdapter (private val taskList: List<Task>): RecyclerView.Adapter<TaskViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        return TaskViewHolder(layoutInflater.inflate(R.layout.task_todo, parent, false))
    }

    override fun getItemCount(): Int {
      return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.render(task)
    }

}