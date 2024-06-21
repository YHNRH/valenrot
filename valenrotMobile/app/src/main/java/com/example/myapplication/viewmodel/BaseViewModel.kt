package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.repo.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(application: Application) : AndroidViewModel(application) {
    abstract var allEntities : LiveData<List<T>>
    abstract var repository : BaseRepository<T>
    abstract var dao : BaseDao<T>

    fun delete(entity: T) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entity)
    }
    fun update(entity: T) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(entity)
    }
    fun add(entity: T) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entity)
    }
}