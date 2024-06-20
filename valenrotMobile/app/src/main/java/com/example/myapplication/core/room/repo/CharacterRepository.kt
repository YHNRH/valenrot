package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.CharacterDao
import com.example.myapplication.core.room.dao.RaceDao
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.CharacterAndRace

class CharacterRepository(private val characterDao: CharacterDao) {

    val allCharacters: LiveData<List<CharacterAndRace>> = characterDao.getCharactersAndRace()

    suspend fun insert(character: Character) {
        characterDao.insert(character)
    }
    suspend fun delete(character: Character){
        characterDao.delete(character)
    }
    suspend fun update(character: Character){
        characterDao.update(character)
    }
}