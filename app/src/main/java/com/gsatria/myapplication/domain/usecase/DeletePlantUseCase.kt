package com.gsatria.myapplication.domain.usecase

import com.gsatria.myapplication.domain.repository.PlantRepository
import javax.inject.Inject

class DeletePlantUseCase @Inject constructor(
    private val repository: PlantRepository
) {
    suspend operator fun invoke(name: String) {
        repository.deletePlant(name)
    }
}