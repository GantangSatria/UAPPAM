package com.gsatria.myapplication.data.repository

import com.gsatria.myapplication.data.remote.api.PlantApiService
import com.gsatria.myapplication.data.remote.dto.toDomain
import com.gsatria.myapplication.domain.model.Plant
import com.gsatria.myapplication.domain.repository.PlantRepository

class PlantRepositoryImpl(private val api: PlantApiService) : PlantRepository {
    override suspend fun getAllPlants(): List<Plant> = api.getAll().data.map { it.toDomain() }

    override suspend fun getPlantDetail(name: String): Plant = api.getDetail(name).toDomain()

    override suspend fun addPlant(name: String, description: String, price: String) {
        api.addPlant(mapOf("plant_name" to name, "description" to description, "price" to price))
    }

    override suspend fun deletePlant(name: String) {
        api.deletePlant(name)
    }

    override suspend fun updatePlant(name: String, description: String, price: String) {
        api.updatePlant(name, mapOf("plant_name" to name, "description" to description, "price" to price))
    }
}
