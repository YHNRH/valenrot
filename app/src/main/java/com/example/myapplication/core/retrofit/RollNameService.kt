package com.example.myapplication.core.retrofit

import retrofit2.Call
import retrofit2.http.*

interface RollNameService {

    @POST("api/general/fantasy_name")
    fun getRandomNames(): Call<String>
}