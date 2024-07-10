package com.example.myapplication.core.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE


@Entity(foreignKeys = [
        ForeignKey(entity = Campaign::class,
            parentColumns = ["uid"],
            childColumns = ["campaignId"],
            onDelete = CASCADE)])
data class Character(
    val campaignId: Long?,
    val temper: Int?,
    override val title: String?,
    override val lastChangeDate: String?
) : BaseEntity()
