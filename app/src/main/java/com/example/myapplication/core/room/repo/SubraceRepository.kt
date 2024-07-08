package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.SubraceDao
import com.example.myapplication.core.room.entity.Subrace

class SubraceRepository(dao: SubraceDao) : BaseRepository<Subrace>(dao)  {

    val allSubraces: LiveData<List<Subrace>> = dao.getAllSubraces()
    override val allEntities: LiveData<List<Subrace>>
        get() = TODO("Not yet implemented")
}