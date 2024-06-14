package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Race

@Dao
interface CampaignDao {
    @Query("SELECT * FROM campaign")
    fun getAll(): List<Campaign>

    @Query("SELECT * FROM campaign WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Campaign>

    @Query("SELECT * FROM campaign WHERE name LIKE :first  LIMIT 1")
    fun findByName(first: String): Campaign

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(campaign : Campaign)

    @Delete
    suspend fun delete(campaign: Campaign)

    @Query("Select * from campaign order by uid ASC")
    fun getAllCampaigns(): LiveData<List<Campaign>>

    @Update
    suspend fun update(campaign: Campaign)
}
