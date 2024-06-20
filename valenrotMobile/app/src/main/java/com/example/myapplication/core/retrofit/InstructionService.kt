package com.example.myapplication.core.retrofit

import retrofit2.Call
import retrofit2.http.*

interface InstructionService {

    @POST("api/upload")
    fun uploadInstruction(): Call<String>
}