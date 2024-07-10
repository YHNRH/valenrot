package com.example.myapplication.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.core.room.dao.CampaignDao
import com.example.myapplication.core.room.dao.CharacterDao
import com.example.myapplication.core.room.dao.SectionDao
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.Section

@Database(entities = [
    Campaign::class,
    Character::class,
    Section::class], version = 19)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
    abstract fun getCampaignDao(): CampaignDao
    abstract fun getSectionDao(): SectionDao

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