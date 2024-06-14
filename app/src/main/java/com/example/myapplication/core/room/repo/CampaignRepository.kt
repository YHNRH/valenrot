package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.CampaignDao
import com.example.myapplication.core.room.dao.RaceDao
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Race

class CampaignRepository(private val campaignDao: CampaignDao) {

    val allCampaigns: LiveData<List<Campaign>> = campaignDao.getAllCampaigns()

    suspend fun insert(campaign: Campaign) {
        campaignDao.insert(campaign)
    }
    suspend fun delete(campaign: Campaign){
        campaignDao.delete(campaign)
    }
    suspend fun update(campaign: Campaign){
        campaignDao.update(campaign)
    }
}