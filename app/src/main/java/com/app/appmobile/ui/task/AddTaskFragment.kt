package com.app.appmobile.ui.task

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.app.appmobile.R
import com.app.appmobile.adapters.ItemAdapter
import com.app.appmobile.data.repositories.TaskRepositoryImpl
import com.app.appmobile.domain.models.TaskModel
import com.app.appmobile.models.Task

class AddTaskFragment : Fragment() {

    private val repository = TaskRepositoryImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button_addTask)
        val description = view.findViewById<EditText>(R.id.editText_add_task)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val bundle = Bundle()
                bundle.putString("text", description.text.toString())
                parentFragmentManager.setFragmentResult("datos", bundle)
            }
        })



    }






}


