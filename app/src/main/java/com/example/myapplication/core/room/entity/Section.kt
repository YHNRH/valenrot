package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Entity
data class Section(
    override val title: String?,
    val text: String?,
    val parentId: Long?,
    override val lastChangeDate: String?
): BaseEntity() {
    @Ignore
    var isExpanded = false

    companion object{
        fun default(): Section {
            return Section(
                "Заголовок",
                "Текст",
                null,
                SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.US).format(Date()))
        }
    }
}

