package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class Campaign(
    override val name: String?,
    override val lastChangeDate: String?
): BaseEntity() {
    @Ignore
    var isExpanded = false
}
