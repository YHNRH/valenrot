package com.example.myapplication.core.retrofit

object Common {
    private val NAMES_BASE_URL = "https://randomall.ru/"
    private val INSTRUCTION_BASE_URL = "https://randomall.ru/"
    val rollNameService: RollNameService
        get() = RetrofitClient.getClient(NAMES_BASE_URL).create(RollNameService::class.java)
    val instructionService: InstructionService
        get() = RetrofitClient.getClient(INSTRUCTION_BASE_URL).create(InstructionService::class.java)
}