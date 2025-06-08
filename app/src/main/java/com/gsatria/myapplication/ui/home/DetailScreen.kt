package com.gsatria.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gsatria.myapplication.R

class DetailScreen : AppCompatActivity() {

    private lateinit var nameTv: TextView
    private lateinit var priceTv: TextView
    private lateinit var descTv: TextView

    private var name = ""
    private var price = ""
    private var description = ""

    private val updateItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val data = it.data
            name = data?.getStringExtra("plant_name") ?: name
            price = data?.getStringExtra("plant_price") ?: price
            description = data?.getStringExtra("plant_description") ?: description

            nameTv.text = name
            priceTv.text = price
            descTv.text = description
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_screen)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        name = intent.getStringExtra("plant_name") ?: "Unknown"
        price = intent.getStringExtra("plant_price") ?: "Unknown"
        description = intent.getStringExtra("plant_description") ?: "No description"

        nameTv = findViewById(R.id.plant_detail_name)
        priceTv = findViewById(R.id.plant_detail_price)
        descTv = findViewById(R.id.plant_detail_description)

        nameTv.text = name
        priceTv.text = price
        descTv.text = description

//        findViewById<TextView>(R.id.plant_detail_name).text = name
//        findViewById<TextView>(R.id.plant_detail_price).text = price
//        findViewById<TextView>(R.id.plant_detail_description).text = description

        findViewById<AppCompatButton>(R.id.plant_detail_btn).setOnClickListener {
            val intent = Intent(this, UpdateItemScreen::class.java).apply {
                putExtra("plant_name", name)
                putExtra("plant_price", price)
                putExtra("plant_description", description)
            }
            updateItemLauncher.launch(intent)
        }
    }
}