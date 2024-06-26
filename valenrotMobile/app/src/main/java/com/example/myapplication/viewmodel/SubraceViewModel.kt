package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.SubraceDao
import com.example.myapplication.core.room.entity.Subrace
import com.example.myapplication.core.room.repo.BaseRepository
import com.example.myapplication.core.room.repo.SubraceRepository

class SubraceViewModel(application: Application) : BaseViewModel<Subrace>(application) {
    override var dao: BaseDao<Subrace> = AppDatabase.getDatabase(application).getSubraceDao()
    override var repository: BaseRepository<Subrace> = SubraceRepository(dao as SubraceDao)
    override var allEntities = (repository as SubraceRepository).allSubraces
}