package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.CharacterDao
import com.example.myapplication.core.room.dao.SectionDao
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.CharacterAndRace
import com.example.myapplication.core.room.entity.Section

class SectionRepository(dao: SectionDao)  : BaseRepository<Section>(dao) {

    val allSections: LiveData<List<Section>> = dao.getAllSections()

}