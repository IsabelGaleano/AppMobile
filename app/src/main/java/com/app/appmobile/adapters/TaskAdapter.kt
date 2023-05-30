package com.app.appmobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.MainActivity
import com.app.appmobile.R
import com.app.appmobile.models.Task
import com.app.appmobile.ui.activity.TodoActivity


class TaskAdapter (private var taskList: List<Task>, private val activity: TodoActivity): RecyclerView.Adapter<TaskViewHolder>(){

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
        holder.buttonUpdate.setOnClickListener {
            val newValue = holder.text.text.toString()
            task.text = newValue
            notifyItemChanged(position)

            activity.onTaskUpdateClick(task, newValue)
        }
    }

}