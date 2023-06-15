package com.app.appmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.app.appmobile.ui.task.AddTaskFragment
import com.app.appmobile.ui.task.TaskListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToFragment(TaskListFragment())

        navigateToFragmentAddTask(AddTaskFragment())


    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun navigateToFragmentAddTask(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_addTask, fragment)
            .commit()
    }


}