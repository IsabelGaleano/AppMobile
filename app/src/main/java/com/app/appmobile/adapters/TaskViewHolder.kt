package com.app.appmobile.adapters

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.models.Task

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val id = view.findViewById<TextView>(R.id.tvIdTask)
    val text = view.findViewById<TextView>(R.id.tvTextTask)
    val done = view.findViewById<CheckBox>(R.id.cbTaskDone)

    fun render(taskModel: Task) {
        id.text = taskModel.id
        text.text = taskModel.text
        done.isChecked = taskModel.done
        done.text = "Done"
    }
}