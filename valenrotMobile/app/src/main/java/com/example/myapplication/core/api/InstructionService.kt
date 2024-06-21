package com.example.myapplication.core.api

import com.example.myapplication.core.room.entity.Race
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/upload")
    fun uploadInstruction(@Body param : List<Race>): Call<String>

    @POST("api/names")
    fun getRandomNames(): Call<Array<String>>
}