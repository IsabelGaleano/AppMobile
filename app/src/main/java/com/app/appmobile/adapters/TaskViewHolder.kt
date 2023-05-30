package com.app.appmobile.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.models.Task

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val id = view.findViewById<EditText>(R.id.tvIdTask)
    val text = view.findViewById<TextView>(R.id.tvTextTask)
    val done = view.findViewById<CheckBox>(R.id.cbTaskDone)
    val buttonUpdate = view.findViewById<Button>(R.id.buttonUpdate)

    fun render(taskModel: Task) {
        id.setText(taskModel.id)
        text.text = taskModel.text
        done.isChecked = taskModel.done
        done.text = "Done"

    }
}