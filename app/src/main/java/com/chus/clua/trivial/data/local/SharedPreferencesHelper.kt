package com.chus.clua.trivial.data.local

import android.content.SharedPreferences
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun <T> put(key: PreferencesKey, value: T) {
        when (value) {
            is Int -> sharedPreferences.edit().putInt(key.value, value).apply()
            else -> throw IllegalArgumentException()
        }
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> get(key: PreferencesKey): T {
        val klass = T::class.java
        return when {
            klass.isAssignableFrom(Integer::class.java) -> sharedPreferences.getInt(key.value, 0) as T
            else -> throw IllegalArgumentException()
        }
    }
}