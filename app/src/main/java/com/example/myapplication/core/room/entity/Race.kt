package com.example.myapplication.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Race(
    @ColumnInfo(name = "strength")      val strength: Int?,
    @ColumnInfo(name = "agility")       val agility: Int?,
    @ColumnInfo(name = "charisma")      val charisma: Int?,
    @ColumnInfo(name = "intelligence")  val intelligence: Int?,
    @ColumnInfo(name = "perception")    val perception: Int?,
    @ColumnInfo(name = "health")        val health: Int?,
    @ColumnInfo(name = "dodge")         val dodge: Int?,
    @ColumnInfo(name = "durability")    val durability: Int?,
    @ColumnInfo(name = "accuracy")      val accuracy: Int?,
    @ColumnInfo(name = "damage")        val damage: Int?,
    @ColumnInfo(name = "description")   val description: String?,
    @ColumnInfo(name = "name")          val name: String?,
    @ColumnInfo(name = "lastChangeDate")val lastChangeDate: String?
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
