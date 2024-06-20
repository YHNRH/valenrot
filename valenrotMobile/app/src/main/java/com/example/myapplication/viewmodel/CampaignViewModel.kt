package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.AppDatabase
import com.example.myapplication.core.room.repo.RaceRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.repo.CampaignRepository
import kotlinx.coroutines.launch

class CampaignViewModel(application: Application) : AndroidViewModel(application) {
    val allCampaigns : LiveData<List<Campaign>>
    private val repository : CampaignRepository

    init {
        val dao = AppDatabase.getDatabase(application).getCampaignDao()
        repository = CampaignRepository(dao)
        allCampaigns = repository.allCampaigns
    }
    fun deleteCampaign(campaign: Campaign) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(campaign)
    }
    fun updateCampaign(campaign: Campaign) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(campaign)
    }
    fun addCampaign(campaign: Campaign) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(campaign)
    }
}