package com.example.mireaapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mireaapp.data.db.Converters
import java.time.LocalDateTime

@Entity(tableName = "events")
@TypeConverters(Converters::class)
data class EventEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "tags") val tags: List<String>,
    @ColumnInfo(name = "image_uri") val imageUri: String?,
)