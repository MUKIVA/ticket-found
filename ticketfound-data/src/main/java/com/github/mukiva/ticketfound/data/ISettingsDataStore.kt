package com.github.mukiva.ticketfound.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ISettingsDataStore {
    fun getLastFromLocation(): Flow<String>
    suspend fun setLastFromLocation(value: String)
}

internal const val DATA_STORE_NAME = "SETTINGS"
internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)

internal class SettingsDataStoreImpl(
    private val dataStore: DataStore<Preferences>
) : ISettingsDataStore {

    override fun getLastFromLocation(): Flow<String> {
        return dataStore.data.map { settings ->
            settings[LAST_FROM_LOCATION_KEY] ?: ""
        }
    }

    override suspend fun setLastFromLocation(value: String) {
        dataStore.edit { settings ->
            settings[LAST_FROM_LOCATION_KEY] = value
        }
    }

    companion object {
        private val LAST_FROM_LOCATION_KEY = stringPreferencesKey("LAST_FROM_LOCATION_KEY")
    }

}

fun createSettingsRepository(
    context: Context
): ISettingsDataStore {
    val applicationContext = context.applicationContext
        ?: error("Is not the applicationContext")

    val dataStore = applicationContext.dataStore
    return SettingsDataStoreImpl(dataStore)
}