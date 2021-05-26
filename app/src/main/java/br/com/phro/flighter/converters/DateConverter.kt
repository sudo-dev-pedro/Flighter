package br.com.phro.flighter.converters

import androidx.room.TypeConverter
import java.util.*

// Será que irá funcionar?
class DateConverter {

    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}