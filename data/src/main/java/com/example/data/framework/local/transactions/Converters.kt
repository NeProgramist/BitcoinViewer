package com.example.data.framework.local.transactions

import androidx.room.TypeConverter
import com.example.core.domain.models.TransactionType
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromType(type: TransactionType?): String? {
        return type?.toString()
    }

    @TypeConverter
    fun toType(value: String?): TransactionType? {
        return value?.let { TransactionType.valueOf(it) }
    }
}
