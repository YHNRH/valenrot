package com.example.myapplication.core.api

object Common {
    private val INSTRUCTION_BASE_URL = "http://192.168.1.52:3000/"
    val apiService: ApiService
        get() = ApiClient.getClient(INSTRUCTION_BASE_URL).create(ApiService::class.java)
}