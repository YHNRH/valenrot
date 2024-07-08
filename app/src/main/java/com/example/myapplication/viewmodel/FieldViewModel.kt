package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.FieldDao_Impl
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.core.room.entity.Field
import com.example.myapplication.core.room.repo.BaseRepository
import com.example.myapplication.core.room.repo.DefinitionRepository
import com.example.myapplication.core.room.repo.FieldRepository

class FieldViewModel(application: Application) : BaseViewModel<Field>(application) {
    override var dao: BaseDao<Field> = AppDatabase.getDatabase(application).getFieldDao()
    override var repository: BaseRepository<Field> = FieldRepository(dao as FieldDao_Impl)
    override var allEntities = repository.allEntities
}