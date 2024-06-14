package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Campaign(
    @ColumnInfo(name = "name")          val name: String?,
    @ColumnInfo(name = "lastChangeDate")val lastChangeDate: String?
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
