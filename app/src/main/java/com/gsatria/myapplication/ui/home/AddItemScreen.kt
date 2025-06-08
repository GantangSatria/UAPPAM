package com.gsatria.myapplication.ui.home

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.gsatria.myapplication.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddItemScreen : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item_screen)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val nameEditText = findViewById<EditText>(R.id.plant_add_name_et)
        val priceEditText = findViewById<EditText>(R.id.plant_add_price_et)
        val descEditText = findViewById<EditText>(R.id.plant_add_description_et)
        val addButton = findViewById<AppCompatButton>(R.id.plant_add_btn)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val price = priceEditText.text.toString().trim()
            val description = descEditText.text.toString().trim()

            if (name.isEmpty() || price.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Semua field wajib diisi!", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    try {
                        viewModel.addPlant(name, description, price)
                        Toast.makeText(this@AddItemScreen, "Berhasil menambahkan tanaman", Toast.LENGTH_SHORT).show()
                        finish()
                    } catch (e: Exception) {
                        Toast.makeText(this@AddItemScreen, "Gagal: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
