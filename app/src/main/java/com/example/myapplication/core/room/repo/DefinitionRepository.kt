package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.DefinitionDao
import com.example.myapplication.core.room.entity.Definition

class DefinitionRepository(dao: DefinitionDao)  : BaseRepository<Definition>(dao) {
    override var allEntities: LiveData<List<Definition>> = dao.get()
}