package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val roll = 0

    fun roll():Int{
        return Random.nextInt(1,21)
    }
}