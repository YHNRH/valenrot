package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.repo.RaceRepository
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.RaceDao
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.RaceWithSubraces
import com.example.myapplication.core.room.repo.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RaceViewModel(application: Application) : BaseViewModel<Race>(application) {
    override var dao: BaseDao<Race> = AppDatabase.getDatabase(application).getRaceDao()
    override var repository: BaseRepository<Race> = RaceRepository(dao as RaceDao)
    override var allEntities = (repository as RaceRepository).allRaces
    var allRacesWithSubraces = (repository as RaceRepository).allRacesWithSubraces

//    fun addAll(entities: List<RaceWithSubraces>, subraceViewModel:SubraceViewModel) = viewModelScope.launch(Dispatchers.IO) {
//        (repository as RaceRepository).deleteAll()
//        entities.forEach {
//                val insertedId = repository.insert(it.race)
//                it.subraces.forEach {
//                    it.raceId = insertedId
//                    subraceViewModel.add(it)
//                }
//            }
//    }
}