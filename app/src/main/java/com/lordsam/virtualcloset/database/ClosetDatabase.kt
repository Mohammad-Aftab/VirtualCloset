package com.lordsam.virtualcloset.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lordsam.virtualcloset.data.ClosetData
import com.lordsam.virtualcloset.utils.DateConverter
import com.lordsam.virtualcloset.utils.UUIDConverter

@Database(entities = [ClosetData::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class ClosetDatabase: RoomDatabase() {
    abstract fun closetDao(): ClosetDatabaseDao
}