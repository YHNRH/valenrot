package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.CharacterDao
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.repo.BaseRepository
import com.example.myapplication.core.room.repo.CharacterRepository

class CharacterViewModel(application: Application) : BaseViewModel<Character>(application) {
    override var dao: BaseDao<Character> = AppDatabase.getDatabase(application).getCharacterDao()
    override var repository: BaseRepository<Character> = CharacterRepository(dao as CharacterDao)
    override var allEntities = (repository as CharacterRepository).allEntities
}