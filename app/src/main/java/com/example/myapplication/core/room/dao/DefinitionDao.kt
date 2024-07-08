package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.core.room.entity.Section

@Dao
interface DefinitionDao : BaseDao<Definition> {
    @Query("Select * from definition order by uid ASC")
    fun get(): LiveData<List<Definition>>
}
