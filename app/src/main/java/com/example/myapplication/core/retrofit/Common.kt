package com.example.myapplication.core.retrofit

object Common {
    private val BASE_URL = "https://randomall.ru/"
    val retrofitService: RollNameService
        get() = RetrofitClient.getClient(BASE_URL).create(RollNameService::class.java)
}