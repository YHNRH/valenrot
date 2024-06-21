package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.core.room.entity.Campaign

@Dao
interface CampaignDao : BaseDao<Campaign> {
    @Query("Select * from campaign order by uid ASC")
    fun getAllCampaigns(): LiveData<List<Campaign>>
}
