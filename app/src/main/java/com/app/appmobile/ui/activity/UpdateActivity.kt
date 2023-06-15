package com.app.appmobile.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.app.appmobile.R
import com.app.appmobile.data.ServiceBuilder
import com.app.appmobile.api.Task.TaskService
import com.app.appmobile.models.Task
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val button = findViewById<TextView>(R.id.saveUpdate)

        val newText = findViewById<EditText>(R.id.itemNameEdit)
        val newDone = findViewById<CheckBox>(R.id.checkBox_doneUpdate)

        val parametros: Bundle? = intent.extras
        var task: Task? = null // Inicializar la variable task fuera del bloque if
        if (parametros != null) {
            val reference: String? = parametros.getString("reference")
            val id: String? = parametros.getString("id")
            val text: String? = parametros.getString("text")
            val done: Boolean? = parametros.getBoolean("done", false)

            if (reference != null && id != null && text != null && done != null) {
                task = Task(reference, id, text, done) //
            }
        }

        button.setOnClickListener {
            Log.e("success", task.toString())
            if (task != null) {
                task.text = newText.text.toString()
                task.done = newDone.isChecked
                updateTask(task)
            }
        }


    }

    private fun updateTask(task: Task) {

        val gson = Gson()
        val json = gson.toJson(task)
        val taskData = gson.fromJson(json, Task::class.java)

        val serviceBuilder = ServiceBuilder.buildService(TaskService::class.java)
        val call = serviceBuilder.putTask(taskData)

        call.enqueue(object : retrofit2.Callback<Task> {

            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val jsonBody = gson.toJson(responseBody)
                    val updatetask = gson.fromJson(jsonBody, Task::class.java)
                    Log.e("success", updatetask.toString())
                    backItemActivity()
                }
            }

            override fun onFailure(call: Call<Task>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
                Log.e("error", t.message.toString())
            }
        })
    }

    private fun backItemActivity() {
        val intent = Intent(this, ItemActivity::class. java )
        startActivity(intent)
    }
}