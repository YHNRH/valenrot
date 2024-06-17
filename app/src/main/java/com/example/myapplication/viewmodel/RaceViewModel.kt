package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.repo.RaceRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.room.entity.Race
import kotlinx.coroutines.launch

class RaceViewModel(application: Application) : AndroidViewModel(application) {
    val allRaces : LiveData<List<Race>>
    private val repository : RaceRepository

    init {
        val dao = AppDatabase.getDatabase(application).getRaceDao()
        repository = RaceRepository(dao)
        allRaces = repository.allNotes
    }
    fun deleteRace(note: Race) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun updateRace(race : Race) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(race)
    }
    fun addRace(race: Race) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(race)
    }
}