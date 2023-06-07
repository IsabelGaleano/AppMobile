package com.app.appmobile.models

import java.lang.ref.PhantomReference

data class Task  (
    var reference: String,
    var id: String,
    var text: String,
    var done: Boolean
)

