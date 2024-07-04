package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.CampaignDao
import com.example.myapplication.core.room.entity.Campaign

class CampaignRepository(dao: CampaignDao) : BaseRepository<Campaign>(dao) {

    val allCampaigns: LiveData<List<Campaign>> = dao.getAllCampaigns()
}