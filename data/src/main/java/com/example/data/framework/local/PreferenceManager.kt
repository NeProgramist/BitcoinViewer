package com.example.data.framework.local

import android.content.Context
import androidx.preference.PreferenceManager

/*
    wrapper for androidx preference manager
 */
class PreferenceManager(context: Context) {
    private val preference = PreferenceManager.getDefaultSharedPreferences(context)

    fun putString(name: String, value: String?) = preference
        .edit()
        .putString(name, value)
        .apply()

    fun putInt(name: String, value: Int) = preference
        .edit()
        .putInt(name, value)
        .apply()

    fun putLong(name: String, value: Long) = preference
        .edit()
        .putLong(name, value)
        .apply()

    fun putBoolean(name: String, value: Boolean) = preference
        .edit()
        .putBoolean(name, value)
        .apply()

    fun putFloat(name: String, value: Float) = preference
        .edit()
        .putFloat(name, value)
        .apply()

    fun getString(
        name: String,
        defValue: String? = STRING_DEF_VALUE,
    ): String? = preference.getString(name, defValue)

    fun getInt(
        name: String,
        defValue: Int = INT_DEF_VALUE,
    ): Int = preference.getInt(name, defValue)

    fun getLong(
        name: String,
        defValue: Long = LONG_DEF_VALUE,
    ): Long = preference.getLong(name, defValue)

    fun getBoolean(
        name: String,
        defValue: Boolean = BOOLEAN_DEF_VALUE,
    ): Boolean = preference.getBoolean(name, defValue)

    fun getFloat(
        name: String,
        defValue: Float = FLOAT_DEF_VALUE,
    ) = preference.getFloat(name, defValue)

    fun delete(name: String) = preference
        .edit()
        .remove(name)
        .apply()

    fun clean() = preference
        .edit()
        .clear()
        .apply()

    companion object {
        val STRING_DEF_VALUE = null
        const val INT_DEF_VALUE = -1
        const val LONG_DEF_VALUE = -1L
        const val BOOLEAN_DEF_VALUE = false
        const val FLOAT_DEF_VALUE = 0f
    }
}
