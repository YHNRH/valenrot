package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.core.room.entity.Race

@Dao
interface RaceDao {
    @Query("SELECT * FROM race")
    fun getAll(): List<Race>

    @Query("SELECT * FROM race WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Race>

    @Query("SELECT * FROM race WHERE name LIKE :first AND " +
            "description LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Race

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(race :Race)

    @Delete
    suspend fun delete(race: Race)

    @Query("Select * from race order by uid ASC")
    fun getAllRaces(): LiveData<List<Race>>

    @Update
    suspend fun update(race: Race)
}
