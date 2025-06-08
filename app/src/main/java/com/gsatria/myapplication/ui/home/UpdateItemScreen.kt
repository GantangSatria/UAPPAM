package com.gsatria.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gsatria.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateItemScreen : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_item_screen)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val name = intent.getStringExtra("plant_name") ?: ""
        val price = intent.getStringExtra("plant_price") ?: ""
        val description = intent.getStringExtra("plant_description") ?: ""

        val nameEt = findViewById<EditText>(R.id.plant_update_name_et)
        val priceEt = findViewById<EditText>(R.id.plant_update_price_et)
        val descEt = findViewById<EditText>(R.id.plant_update_description_et)
        val btnSave = findViewById<AppCompatButton>(R.id.plant_update_btn)

        nameEt.setText(name)
        priceEt.setText(price)
        descEt.setText(description)

        btnSave.setOnClickListener {
            val updatedName = nameEt.text.toString()
            val updatedPrice = priceEt.text.toString()
            val updatedDescription = descEt.text.toString()

            if (updatedName.isBlank() || updatedPrice.isBlank() || updatedDescription.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.updatePlant(updatedName, updatedDescription, updatedPrice) { success ->
                if (success) {
                    val resultIntent = Intent().apply {
                        putExtra("plant_name", updatedName)
                        putExtra("plant_price", updatedPrice)
                        putExtra("plant_description", updatedDescription)
                    }
                    setResult(RESULT_OK, resultIntent)
                    Toast.makeText(this, "Plant updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to update plant", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}