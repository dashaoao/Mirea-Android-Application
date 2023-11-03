package com.example.mireaapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mireaapp.data.db.dao.EventDao
import com.example.mireaapp.data.db.entity.EventEntity

@Database(
    entities = [EventEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}