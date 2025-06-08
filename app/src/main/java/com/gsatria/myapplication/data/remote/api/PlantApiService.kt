package com.gsatria.myapplication.data.remote.api

import com.gsatria.myapplication.data.remote.dto.PlantDto
import com.gsatria.myapplication.data.remote.dto.PlantListResponse
import retrofit2.http.*

interface PlantApiService {
    @GET("plant/all")
    suspend fun getAll(): PlantListResponse

    @GET("plant/{plant_name}")
    suspend fun getDetail(@Path("plant_name") name: String): PlantDto

    @POST("plant/new")
    suspend fun addPlant(@Body body: Map<String, String>)

    @PUT("plant/{plant_name}")
    suspend fun updatePlant(
        @Path("plant_name") name: String,
        @Body body: Map<String, String>
    )

    @DELETE("plant/{plant_name}")
    suspend fun deletePlant(@Path("plant_name") name: String)
}