package com.example.myapplication.core.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
interface BaseDao<T> {
    @Insert
    suspend fun insert(entity: T)
    @Delete
    suspend fun delete(entity: T)
    @Update
    suspend fun update(entity: T)
}