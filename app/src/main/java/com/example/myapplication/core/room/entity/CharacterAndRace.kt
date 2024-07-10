package com.example.myapplication.core.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterAndRace(
    @Embedded val character: Character
)