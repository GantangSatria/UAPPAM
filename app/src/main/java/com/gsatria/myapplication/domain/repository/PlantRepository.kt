package com.gsatria.myapplication.domain.repository

import com.gsatria.myapplication.domain.model.Plant

interface PlantRepository {
    suspend fun getAllPlants(): List<Plant>
    suspend fun getPlantDetail(name: String): Plant
    suspend fun addPlant(name: String, description: String, price: String)
    suspend fun deletePlant(name: String)
    suspend fun updatePlant(name: String, description: String, price: String)
}