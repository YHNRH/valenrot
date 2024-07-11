package com.example.myapplication.core.room.entity

import androidx.room.Entity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Entity
data class Campaign(
    override val title: String?,
    override val lastChangeDate: String?
): BaseEntity() {

    companion object {
        fun default() = Campaign("Campaign",
                SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.US).format(Date()))
    }
}
