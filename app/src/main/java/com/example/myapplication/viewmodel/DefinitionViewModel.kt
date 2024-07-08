package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.DefinitionDao
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.core.room.repo.BaseRepository
import com.example.myapplication.core.room.repo.DefinitionRepository
import com.example.myapplication.core.room.repo.SubraceRepository

class DefinitionViewModel(application: Application) : BaseViewModel<Definition>(application) {
    override var dao: BaseDao<Definition> = AppDatabase.getDatabase(application).getDefinitionDao()
    override var repository: BaseRepository<Definition> = DefinitionRepository(dao as DefinitionDao)
    override var allEntities = repository.allEntities
}