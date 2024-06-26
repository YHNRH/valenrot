package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.core.room.entity.Subrace

@Dao
interface SubraceDao : BaseDao<Subrace> {
    @Query("Select * from subrace order by uid ASC")
    fun getAllSubraces(): LiveData<List<Subrace>>
}
