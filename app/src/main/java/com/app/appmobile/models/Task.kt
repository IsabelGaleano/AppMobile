package com.app.appmobile.models

import java.lang.ref.PhantomReference

data class Task(
    val reference: String,
    val id: String,
    val text: String,
    val done: Boolean
)

