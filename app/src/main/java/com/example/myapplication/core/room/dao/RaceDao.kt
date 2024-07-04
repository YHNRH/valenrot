package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.RaceWithSubraces

@Dao
interface RaceDao : BaseDao<Race> {
    @Query("Select * from race order by uid ASC")
    fun getAllRaces(): LiveData<List<Race>>

    @Transaction
    @Query("Select * from race order by uid ASC")
    fun getAllRacesWithSubraces(): LiveData<List<RaceWithSubraces>>

    @Query("DELETE FROM race")
    suspend fun deleteAll()
}
