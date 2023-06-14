package com.app.appmobile.ui.task

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.R
import com.app.appmobile.databinding.TaskItemLayoutBinding
import com.app.appmobile.domain.models.TaskModel
import com.app.appmobile.ui.task.adapters.TaskListAdapter
import com.app.appmobile.ui.task.viewmodels.TaskListViewModel

class TaskListFragment : Fragment(), TaskListAdapter.TaskClickListener {

    private lateinit var viewModel: TaskListViewModel
    private lateinit var listRecyclerView: RecyclerView

    private var adapter = TaskListAdapter(this, this)

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

    private fun onListItemClicked(taskModel: TaskModel) {
        Toast.makeText(requireContext(), "${taskModel.text} was clicked", Toast.LENGTH_LONG).show()
        Log.e("success", taskModel.toString())
        // Todo remove item
    }

    override fun onTaskClickListener(item: TaskModel, position: Int) {
        Toast.makeText(requireContext(), "${item.text} was clicked", Toast.LENGTH_LONG).show()
        Log.e("success", item.toString())
    }


}