package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.Race

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): List<Race>

    @Query("SELECT * FROM race WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Race>

    @Query("SELECT * FROM race WHERE name LIKE :first AND " +
            "description LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Race

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)

    @Delete
    suspend fun delete(character: Character)

    @Query("Select * from character order by uid ASC")
    fun getAllCharacters(): LiveData<List<Character>>

    @Update
    suspend fun update(character: Character)
}
