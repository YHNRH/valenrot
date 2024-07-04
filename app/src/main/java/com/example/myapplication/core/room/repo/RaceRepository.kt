package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.RaceDao
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.RaceWithSubraces

class RaceRepository(dao: RaceDao) : BaseRepository<Race>(dao)  {

    val allRaces: LiveData<List<Race>> = dao.getAllRaces()
    val allRacesWithSubraces: LiveData<List<RaceWithSubraces>> = dao.getAllRacesWithSubraces()

    suspend fun deleteAll(){
        (dao as RaceDao).deleteAll()
    }
}