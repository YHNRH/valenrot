package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Character(
    @ColumnInfo(name = "raceId")            val raceId: Int?,
    @ColumnInfo(name = "name")              val name: String?,
    @ColumnInfo(name = "campaignId")        val campaignId: Int?,
    @ColumnInfo(name = "temper")            val temper: Int?,
    @ColumnInfo(name = "lastChangeDate")    val lastChangeDate: String?
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
