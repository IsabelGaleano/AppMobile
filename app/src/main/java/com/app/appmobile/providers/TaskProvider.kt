package com.app.appmobile.providers

import com.app.appmobile.models.Task

class TaskProvider {
    companion object {
        val taskList = listOf<Task>(
            Task("1", "Tarea 1", false),
            Task("2", "Tarea 2", true),
            Task("3", "Tarea 3", false),
            Task("4", "Tarea 4", true),
            Task("5", "Tarea 5", false)
        )
    }
}