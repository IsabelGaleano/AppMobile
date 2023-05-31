package com.app.appmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.appmobile.ui.activity.ItemActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.buttonList)
        buttonClick.setOnClickListener {
            val intent = Intent(this, ItemActivity::class. java )
            startActivity(intent)
        }
    }
}