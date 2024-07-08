package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.dao.BaseDao
import com.example.myapplication.core.room.dao.CampaignDao
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.repo.BaseRepository
import com.example.myapplication.core.room.repo.CampaignRepository

class CampaignViewModel(application: Application) : BaseViewModel<Campaign>(application) {
    override var dao: BaseDao<Campaign> = AppDatabase.getDatabase(application).getCampaignDao()
    override var repository: BaseRepository<Campaign> = CampaignRepository(dao as CampaignDao)
    override var allEntities = (repository as CampaignRepository).allEntities

}