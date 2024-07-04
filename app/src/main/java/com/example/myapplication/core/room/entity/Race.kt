package com.example.myapplication.core.room.entity

import androidx.room.Entity
import androidx.room.Ignore
import java.text.SimpleDateFormat
import java.util.Date

@Entity
data class Race(
    val strength: Int?,
    val agility: Int?,
    val charisma: Int?,
    val intelligence: Int?,
    val perception: Int?,
    val health: Int?,
    val dodge: Int?,
    val durability: Int?,
    val accuracy: Int?,
    val damage: String?,
    val description: String?,
    override var name: String?,
    override var lastChangeDate: String?
):BaseEntity(){
    @Ignore
    var isExpanded = false

    companion object{
        fun default(): Race {
            return Race(
                1,
                3,
                54,
                5,
                1,
                1,
                1,
                1,
                1,
                "1",
                "Описание",
                "Новая раса",
                SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date()))
        }
    }
}
