package com.example.myapplication.core.room.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.dao.CampaignDao
import com.example.myapplication.core.room.entity.Campaign

class CampaignRepository(dao: CampaignDao) : BaseRepository<Campaign>(dao) {

    override val allEntities: LiveData<List<Campaign>> = dao.getAllCampaigns()
}