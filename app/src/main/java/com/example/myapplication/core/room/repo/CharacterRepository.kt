package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.CharacterDao
import com.example.myapplication.core.room.entity.Character

class CharacterRepository(dao: CharacterDao)  : BaseRepository<Character>(dao) {

    override val allEntities: LiveData<List<Character>> = dao.getCharacters()


}