package com.example.myapplication.core.room.entity

import androidx.room.PrimaryKey

abstract class BaseEntity{
    abstract val name: String?
    abstract val lastChangeDate: String?
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
}