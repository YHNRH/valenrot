package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.RaceDao
import com.example.myapplication.core.room.entity.Race

class RaceRepository(private val raceDao: RaceDao) {

    val allNotes: LiveData<List<Race>> = raceDao.getAllRaces()

    suspend fun insert(race: Race) {
        raceDao.insert(race)
    }
    suspend fun delete(race: Race){
        raceDao.delete(race)
    }
    suspend fun update(race: Race){
        raceDao.update(race)
    }
}