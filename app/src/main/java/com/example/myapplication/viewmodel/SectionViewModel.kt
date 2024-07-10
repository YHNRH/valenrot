package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.SectionDao
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.core.room.repo.BaseRepository
import com.example.myapplication.core.room.repo.SectionRepository

class SectionViewModel(application: Application) : BaseViewModel<Section>(application) {
    override var dao: BaseDao<Section> = AppDatabase.getDatabase(application).getSectionDao()
    override var repository: BaseRepository<Section> = SectionRepository(dao as SectionDao)
    override var allEntities = (repository as SectionRepository).allSections
}