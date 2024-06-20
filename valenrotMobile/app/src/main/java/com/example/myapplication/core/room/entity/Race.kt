package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Race(
    val strength: Int?,
    val agility: Int?,
    val charisma: Int?,
    val intelligence: Int?,
    val perception: Int?,
    val health: Int?,
    val dodge: Int?,
    val durability: Int?,
    val accuracy: Int?,
    val damage: Int?,
    val description: String?,
    override val name: String?,
    override val lastChangeDate: String?
):BaseEntity()
