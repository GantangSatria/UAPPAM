package com.gsatria.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gsatria.myapplication.databinding.ActivityHomeScreenBinding
import com.gsatria.myapplication.domain.model.Plant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreen : AppCompatActivity(), PlantAdapter.OnItemClickListener {

    private lateinit var binding: ActivityHomeScreenBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: PlantAdapter

    private val addItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            viewModel.loadPlants()
        }
    }

    private val updateItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        viewModel.loadPlants()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PlantAdapter(this)
        binding.homeRecyler.layoutManager = LinearLayoutManager(this)
        binding.homeRecyler.adapter = adapter



        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.plants.collect { plantList ->
                    adapter.submitList(plantList)
                }
            }
        }
//        lifecycleScope.launchWhenStarted {
//            viewModel.plants.collectLatest { plantList ->
//                adapter = PlantAdapter(plantList, this@HomeScreen)
//                binding.homeRecyler.adapter = adapter
//            }
//        }

        binding.tambahBtn.setOnClickListener {
            val intent = Intent(this, AddItemScreen::class.java)
            addItemLauncher.launch(intent)
        }
    }

            override fun onDeleteClick(plant: Plant) {
                viewModel.deletePlant(plant)
            }

            override fun onDetailClick(plant: Plant) {
                val intent = Intent(this, DetailScreen::class.java).apply {
                    putExtra("plant_name", plant.plantName)
                    putExtra("plant_price", plant.price)
                    putExtra("plant_description", plant.description)
                }
                updateItemLauncher.launch(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPlants()
    }
}