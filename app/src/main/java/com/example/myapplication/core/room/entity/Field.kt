package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class Field(
    override var title: String?,
    val parentId : Long?,
    var type: String?,
    override val lastChangeDate: String?
): BaseEntity()