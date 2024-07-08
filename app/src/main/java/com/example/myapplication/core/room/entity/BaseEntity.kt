package com.example.myapplication.core.room.entity

import androidx.room.PrimaryKey

abstract class BaseEntity{
    abstract val title: String?
    abstract val lastChangeDate: String?
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
}