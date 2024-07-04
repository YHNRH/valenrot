package com.example.myapplication.core.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE


@Entity(foreignKeys = [
        ForeignKey(entity = Race::class,
            parentColumns = ["uid"],
            childColumns = ["raceId"],
            onDelete = CASCADE),
        ForeignKey(entity = Campaign::class,
            parentColumns = ["uid"],
            childColumns = ["campaignId"],
            onDelete = CASCADE)])
data class Character(
    val raceId: Long?,
    val campaignId: Long?,
    val temper: Int?,
    override val name: String?,
    override val lastChangeDate: String?
) : BaseEntity()
