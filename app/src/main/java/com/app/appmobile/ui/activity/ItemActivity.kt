package com.app.appmobile.ui.activity

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.adapters.ItemAdapter
import com.app.appmobile.api.ServiceBuilder
import com.app.appmobile.api.Task.TaskService
import com.app.appmobile.databinding.ActivityItemBinding
import com.app.appmobile.databinding.PopupEditItemBinding
import com.app.appmobile.models.Item
import com.app.appmobile.models.Task
import com.app.appmobile.models.TaskResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class ItemActivity: AppCompatActivity(), ItemAdapter.ItemClickListener {

    private lateinit var binding: ActivityItemBinding
    private var adapter = ItemAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()


        binding.buttonAddTaskA.setOnClickListener {
            registerTask(binding.edAddTask.text.toString())
        }
    }

    private fun setupUi() {
        loadData()
    }

    private fun loadData() {
        val serviceBuilder = ServiceBuilder.buildService(TaskService::class.java)
        val call = serviceBuilder.getTasks()

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

    private fun initRecyclerView(taskList: List<Task>) {
        initRecyclerView(binding.recycler)
        binding.recycler.adapter = adapter
        adapter.addAll(taskList)
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    override fun onItemClickListener(item: Task, position: Int) {
        val dialogBinding: PopupEditItemBinding =
            PopupEditItemBinding.inflate(
                LayoutInflater.from(this)
            )
        val dialog = Dialog(this)
        val itemNameEdit = dialogBinding.itemNameEdit
        val save = dialogBinding.save
        val cancel = dialogBinding.cancel

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        save.setOnClickListener {
            if (!TextUtils.isEmpty(itemNameEdit.text.toString())) {
                adapter.updateItem(position, itemNameEdit.text.toString())
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter a new name", Toast.LENGTH_SHORT).show()
            }
        }

        cancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
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

    private fun getDummyList(): ArrayList<Item> {
        val items = ArrayList<Item>()
        items.add(Item("1","1", "Tarea 1", false))
        items.add(Item("2", "2", "Tarea 2", true))
        items.add(Item("3", "3", "Tarea 3", false))
        return items
    }

}