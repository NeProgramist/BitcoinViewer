package com.example.data.framework.local.transactions

import androidx.room.TypeConverter
import com.example.core.domain.models.TransactionType
import java.util.*

class DateConverters {
    @TypeConverter
    fun toDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}

class TransactionTypeConverters {

    @TypeConverter
    fun fromType(type: TransactionType): String {
        return type.toString()
    }

    @TypeConverter
    fun toType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }
}

class UUIDConverter {
    @TypeConverter
    fun fromUUID(id: UUID): String {
        return id.toString()
    }

    @TypeConverter
    fun fromUUID(value: String): UUID {
        return UUID.fromString(value)
    }
}

