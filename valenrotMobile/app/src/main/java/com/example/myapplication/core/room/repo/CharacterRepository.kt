package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.CharacterDao
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.CharacterAndRace

class CharacterRepository(dao: CharacterDao)  : BaseRepository<Character>(dao) {

    val allCharacters: LiveData<List<CharacterAndRace>> = dao.getCharactersAndRace()

}