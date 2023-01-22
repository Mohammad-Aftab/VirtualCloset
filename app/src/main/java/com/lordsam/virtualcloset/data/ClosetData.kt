package com.lordsam.virtualcloset.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "closet_table")
data class ClosetData @RequiresApi(Build.VERSION_CODES.O) constructor(

    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "date_time")
    val dateTime: Date = Date.from(Instant.now()),

    @ColumnInfo(name = "uri")
    val uri: String
)
