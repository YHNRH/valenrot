package com.example.myapplication.core.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class RaceWithSubraces(
    @Embedded val race: Race,
    @Relation(
        parentColumn = "uid",
        entityColumn = "raceId"
    )
    val subraces: List<Subrace>
)