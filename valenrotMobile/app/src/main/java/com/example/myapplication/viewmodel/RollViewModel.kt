package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class RollViewModel : ViewModel() {
    val roll = 0

    fun roll():Int{
        return Random.nextInt(1,21)
    }
}