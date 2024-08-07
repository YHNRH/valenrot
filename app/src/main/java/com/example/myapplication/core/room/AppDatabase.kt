package com.example.myapplication.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.core.room.dao.CampaignDao
import com.example.myapplication.core.room.dao.CharacterDao
import com.example.myapplication.core.room.dao.RaceDao
import com.example.myapplication.core.room.dao.SubraceDao
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.Subrace

@Database(entities = [Race::class, Campaign::class, Character::class, Subrace::class], version = 10)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRaceDao(): RaceDao
    abstract fun getSubraceDao(): SubraceDao
    abstract fun getCharacterDao(): CharacterDao
    abstract fun getCampaignDao(): CampaignDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}