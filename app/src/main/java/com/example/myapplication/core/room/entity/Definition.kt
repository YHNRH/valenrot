package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date


@Entity
data class Definition(
    override var title: String?,
    override val lastChangeDate: String?
): BaseEntity() {
    @Ignore
    var isExpanded = false

    companion object{
        fun default(): Definition {
            return Definition(
                "Новая раса",
                SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date()))
        }
    }
}
