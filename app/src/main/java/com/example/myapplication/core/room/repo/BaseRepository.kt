package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.BaseDao
import io.reactivex.Single
import java.util.concurrent.Callable


abstract class BaseRepository<T>(val dao: BaseDao<T>) {
    suspend fun insert(entity: T): Long {
        return dao.insert(entity)
    }

    suspend fun delete(entity: T){
        dao.delete(entity)
    }
    suspend fun update(entity: T){
        dao.update(entity)
    }

    abstract val allEntities: LiveData<List<T>>
}