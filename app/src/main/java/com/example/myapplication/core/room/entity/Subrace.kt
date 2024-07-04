package com.example.myapplication.core.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(foreignKeys = [
    ForeignKey(entity = Race::class,
        parentColumns = ["uid"],
        childColumns = ["raceId"],
        onDelete = ForeignKey.CASCADE
    )])
data class Subrace(override var name: String?,
                   val description: String?,
                   val activeAbility: String?,
                   val passiveAbility: String?,
                   var raceId: Long,
                   override var lastChangeDate: String?
):BaseEntity()