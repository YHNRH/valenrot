package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.repo.RaceRepository
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.RaceDao
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.repo.BaseRepository

class RaceViewModel(application: Application) : BaseViewModel<Race>(application) {
    override var dao: BaseDao<Race> = AppDatabase.getDatabase(application).getRaceDao()
    override var repository: BaseRepository<Race> = RaceRepository(dao as RaceDao)
    override var allEntities = (repository as RaceRepository).allRaces
}