package com.app.appmobile.ui.task

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.data.repositories.TaskRepositoryImpl
import com.app.appmobile.databinding.TaskItemLayoutBinding
import com.app.appmobile.domain.models.TaskModel
import com.app.appmobile.ui.task.adapters.TaskListAdapter
import com.app.appmobile.ui.task.viewmodels.TaskListViewModel

class TaskListFragment : Fragment(), TaskListAdapter.TaskClickListener {

    private lateinit var viewModel: TaskListViewModel
    private lateinit var listRecyclerView: RecyclerView
    private val repository = TaskRepositoryImpl()

    var adapter = TaskListAdapter(this, this)

    companion object {
        fun newInstance(): TaskListFragment {
            return TaskListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        initViews(view)
        viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
        observe()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewReady()

        parentFragmentManager.setFragmentResultListener("datos", this) { _, result ->
            val text = result.getString("text")
            val task = TaskModel("","123675", text.toString(), false)
            addTask(task)
            Log.e("success", text.toString())
        }



    }

    private fun initViews(view: View) {
        with(view) {
            listRecyclerView = findViewById(R.id.task_list)
            listRecyclerView.adapter = adapter
            listRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            listRecyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun observe() {
        viewModel.noteListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }
    }

    private fun addTask(_task: TaskModel) {
        val callback: (TaskModel?) -> Unit = { task ->
            if (task != null) {
                adapter.addTask(task)

                println("TaskModel recibido: $task")
            } else {

                println("No se recibió ningún TaskModel")
            }
        }
        repository.addTask(_task, callback)


    }



    override fun onTaskClickListener(item: TaskModel, position: Int) {
        Toast.makeText(requireContext(), "${item.text} was clicked", Toast.LENGTH_LONG).show()
        Log.e("success", item.toString())

        val bundle = Bundle()
        bundle.putString("task_text_update", item.text)
        bundle.putBoolean("task_done_update", item.done)
        bundle.putString("task_id_update", item.id)
        bundle.putString("task_reference_update", item.reference)
        parentFragmentManager.setFragmentResult("task_update", bundle)

        cambiarFragmento()
    }

    private fun cambiarFragmento() {
        val fragmentManager: FragmentManager = parentFragmentManager
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragmentList: List<Fragment> = fragmentManager.fragments
        for (fragment in fragmentList) {
            fragmentTransaction.replace(fragment.id, UpdateTaskFragment())
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


    }


}