package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.CharacterAndRace

@Dao
interface CharacterDao : BaseDao<Character>  {
    @Transaction
    @Query("SELECT * FROM character order by uid ASC")
    fun getCharactersAndRace(): LiveData<List<CharacterAndRace>>
    @Query("SELECT * FROM character order by uid ASC")
    fun getCharacters(): LiveData<List<Character>>
}
