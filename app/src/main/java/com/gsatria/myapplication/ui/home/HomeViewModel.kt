package com.gsatria.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsatria.myapplication.domain.model.Plant
import com.gsatria.myapplication.domain.usecase.AddPlantUseCase
import com.gsatria.myapplication.domain.usecase.DeletePlantUseCase
import com.gsatria.myapplication.domain.usecase.GetAllPlantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPlantsUseCase: GetAllPlantsUseCase,
    private val deletePlantUseCase: DeletePlantUseCase,
    private val addPlantUseCase: AddPlantUseCase,
) : ViewModel() {

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> get() = _plants

    init {
        loadPlants()
    }

    fun loadPlants() {
        viewModelScope.launch {
            _plants.value = getAllPlantsUseCase()
        }
    }

    fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            deletePlantUseCase(plant.plantName)
            loadPlants()
        }
    }

    fun addPlant(name: String, description: String, price: String) {
        viewModelScope.launch {
            try {
                addPlantUseCase(name, description, price)
                getAllPlantsUseCase()
            } catch (e: Exception) {
                Log.e("AddPlant", "Gagal menambahkan tanaman", e)
            }
        }
    }
}