package com.example.myapplication.core.room.entity

import androidx.room.Entity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Entity
data class Section(
    override val title: String?,
    val text: String?,
    var parentId: Long?,
    override val lastChangeDate: String?,
    var isList: Boolean = false
): BaseEntity() {

    companion object{
        fun default() = Section(
                "Заголовок",
                "Текст",
                null,
                SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.US).format(Date()))
    }
}

