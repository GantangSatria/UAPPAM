package com.gsatria.myapplication.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gsatria.myapplication.R

class DetailScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_screen)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val name = intent.getStringExtra("plant_name") ?: "Unknown"
        val price = intent.getStringExtra("plant_price") ?: "Unknown"
        val description = intent.getStringExtra("plant_description") ?: "No description"

        findViewById<TextView>(R.id.plant_detail_name).text = name
        findViewById<TextView>(R.id.plant_detail_price).text = price
        findViewById<TextView>(R.id.plant_detail_description).text = description

    }
}