package com.example.myapplication.ui.raceeditor

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Race

class RaceEditViewModel : ViewModel() {
    fun getRaces(): Array<Race> {
        return arrayOf(
            Race(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                "Это тестовое описание"
            )
        )
    }
}