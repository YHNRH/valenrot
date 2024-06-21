package com.example.myapplication.core.room.repo

import com.example.myapplication.core.room.dao.BaseDao

abstract class BaseRepository<T>(val dao: BaseDao<T>) {
    suspend fun insert(entity: T) {
        dao.insert(entity)
    }
    suspend fun delete(entity: T){
        dao.delete(entity)
    }
    suspend fun update(entity: T){
        dao.update(entity)
    }
}