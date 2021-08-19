package com.example.sampledatastore.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")
private const val PREF_1: String = "pref1"
private const val PREF_2: String = "pref2"

class UserPrefsKotlin(context: Context) {
    private val mDataStore: DataStore<Preferences> = context.dataStore
    private val mAppContext = context.applicationContext

    suspend fun writePref(value1: String, value2: String) {
        mDataStore.edit {
            it[PREF_KEY_1] = value1
            it[PREF_KEY_2] = value2
        }
    }

    val pref1: Flow<String?> = mAppContext.dataStore.data.map {
        it[PREF_KEY_1] ?: "NA"
    }

    val pref2: Flow<String?> = mAppContext.dataStore.data.map {
        it[PREF_KEY_2] ?: "NA"
    }

    suspend fun clear() {
        mAppContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val PREF_KEY_1 = stringPreferencesKey(PREF_1)
        private val PREF_KEY_2 = stringPreferencesKey(PREF_2)
    }
}
