package com.gsatria.myapplication.di

import com.gsatria.myapplication.data.remote.api.PlantApiService
import com.gsatria.myapplication.data.repository.PlantRepositoryImpl
import com.gsatria.myapplication.domain.repository.PlantRepository
import com.gsatria.myapplication.domain.usecase.DeletePlantUseCase
import com.gsatria.myapplication.domain.usecase.GetAllPlantsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providePlantApi(): PlantApiService = Retrofit.Builder()
        .baseUrl("https://uappam.kuncipintu.my.id/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PlantApiService::class.java)

    @Provides
    fun providePlantRepository(api: PlantApiService): PlantRepository =
        PlantRepositoryImpl(api)

    @Provides
    fun provideGetAllPlantsUseCase(repository: PlantRepository): GetAllPlantsUseCase {
        return GetAllPlantsUseCase(repository)
    }

    @Provides
    fun provideDeletePlantUseCase(repository: PlantRepository): DeletePlantUseCase {
        return DeletePlantUseCase(repository)
    }
}