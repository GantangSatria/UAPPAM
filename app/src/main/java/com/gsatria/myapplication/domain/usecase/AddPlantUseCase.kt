package com.gsatria.myapplication.domain.usecase

import com.gsatria.myapplication.domain.repository.PlantRepository
import javax.inject.Inject

class AddPlantUseCase @Inject constructor(
    private val repository: PlantRepository
) {
    suspend operator fun invoke(name: String, description: String, price: String) {
        repository.addPlant(name, description, price)
    }
}