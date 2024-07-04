package com.example.myapplication.core.api

object Common {
    private val INSTRUCTION_BASE_URL = "http://api.sarkofiton.keenetic.link/"
    val apiService: ApiService
        get() = ApiClient.getClient(INSTRUCTION_BASE_URL).create(ApiService::class.java)
}