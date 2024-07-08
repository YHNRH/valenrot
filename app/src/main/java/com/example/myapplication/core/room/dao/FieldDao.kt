package com.example.myapplication.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.core.room.entity.Field

@Dao
interface FieldDao : BaseDao<Field> {
    @Query("Select * from field order by uid ASC")
    fun get(): LiveData<List<Field>>
}
