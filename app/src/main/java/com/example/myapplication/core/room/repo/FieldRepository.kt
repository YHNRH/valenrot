package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.FieldDao
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.core.room.entity.Field

class FieldRepository(dao: FieldDao)  : BaseRepository<Field>(dao) {
    override var allEntities: LiveData<List<Field>> = dao.get()
}