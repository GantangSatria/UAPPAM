package com.gsatria.myapplication.domain.usecase

import com.gsatria.myapplication.domain.repository.PlantRepository
import javax.inject.Inject

class GetAllPlantsUseCase @Inject constructor(private val repo: PlantRepository) {
    suspend operator fun invoke() = repo.getAllPlants()
}