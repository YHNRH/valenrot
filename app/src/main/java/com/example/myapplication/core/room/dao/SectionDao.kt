package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Section

@Dao
interface SectionDao : BaseDao<Section> {
    @Query("Select * from section order by uid ASC")
    fun getAllSections(): LiveData<List<Section>>
}
