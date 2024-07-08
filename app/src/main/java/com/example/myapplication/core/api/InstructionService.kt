package com.example.myapplication.core.api

import com.example.myapplication.core.room.entity.RaceWithSubraces
import com.example.myapplication.core.room.entity.Section
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/upload")
    fun uploadInstruction(@Body param : List<Section>): Call<String>
    @GET("api/download")
    fun downloadInstruction(): Call<List<RaceWithSubraces>>

    @POST("api/names")
    fun getRandomNames(): Call<Array<String>>
}