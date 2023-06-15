package com.app.appmobile.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.appmobile.R
import com.app.appmobile.data.repositories.TaskRepositoryImpl
import com.app.appmobile.domain.models.TaskModel

class UpdateTaskFragment : Fragment() {

    private val repository = TaskRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<TextView>(R.id.button_save_task_update)

        val updateText = view.findViewById<EditText>(R.id.update_task_text)
        val doneTask = view.findViewById<CheckBox>(R.id.checkBox_task_done)

        button.setOnClickListener {

            parentFragmentManager.setFragmentResultListener("task_update", this) { _, result ->
                val id = result.getString("task_id_update")
                val reference = result.getString("task_reference_update")

                Log.e("success", reference.toString())

                val task = TaskModel(reference.toString(),id.toString(), updateText.text.toString(), doneTask.isChecked)
                updateTask(task)

            }

        }
    }

    private fun updateTask(_task: TaskModel) {
        val callback: (TaskModel?) -> Unit = { task ->
            if (task != null) {
               cambiarFragmento()

                println("TaskModel actualizado: $task")
            } else {

                println("No se recibió ningún TaskModel")
            }
        }
        repository.updateTask(_task, callback)


    }

    private fun cambiarFragmento() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()


    }
}