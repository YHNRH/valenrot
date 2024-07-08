package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Definition

interface BaseDao<T> {
    @Insert
    suspend fun insert(entity: T): Long
    @Delete
    suspend fun delete(entity: T)
    @Update
    suspend fun update(entity: T)
//    fun get(): LiveData<List<T>>
}