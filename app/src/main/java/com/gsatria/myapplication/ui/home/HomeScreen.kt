package com.gsatria.myapplication.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gsatria.myapplication.databinding.ActivityHomeScreenBinding
import com.gsatria.myapplication.domain.model.Plant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeScreen : AppCompatActivity(), PlantAdapter.OnItemClickListener {

    private lateinit var binding: ActivityHomeScreenBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeRecyler.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenStarted {
            viewModel.plants.collectLatest { plantList ->
                adapter = PlantAdapter(plantList, this@HomeScreen)
                binding.homeRecyler.adapter = adapter
            }
        }

        binding.tambahBtn.setOnClickListener {
            // todo
        }
    }

    override fun onDeleteClick(plant: Plant) {
        viewModel.deletePlant(plant)
    }

    override fun onDetailClick(plant: Plant) {
        // todo
    }
}