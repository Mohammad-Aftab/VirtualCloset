package com.lordsam.virtualcloset.utils

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.*

class DateConverter {

    @TypeConverter
    fun timeStampFromDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun dataFromTimestamp(timestamp: Long): Date?{
        return Date(timestamp)
    }
}