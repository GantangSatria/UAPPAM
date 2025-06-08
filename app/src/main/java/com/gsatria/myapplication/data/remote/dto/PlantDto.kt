package com.gsatria.myapplication.data.remote.dto

import com.gsatria.myapplication.domain.model.Plant

data class PlantDto (
    val id: Int,
    val plant_name: String,
    val description: String,
    val price: String
)

data class PlantListResponse (
    val status: String,
    val data: List<PlantDto>
)

fun PlantDto.toDomain(): Plant = Plant(
    id = id,
    plantName = plant_name,
    description = description,
    price = price
)