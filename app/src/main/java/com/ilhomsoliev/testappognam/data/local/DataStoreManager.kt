package com.ilhomsoliev.testappognam.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class DataStoreManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")
        val token_key = stringPreferencesKey("token_key")
        val refresh_token_key = stringPreferencesKey("refresh_token_key")
        val phone_key = stringPreferencesKey("phone_key")
    }

    suspend fun changeToken(value: String) {
        context.dataStore.edit { preferences ->
            preferences[token_key] = value
        }
    }

    suspend fun changeRefreshToken(value: String) {
        context.dataStore.edit { preferences ->
            preferences[refresh_token_key] = value
        }
    }
    suspend fun changePhone(value: String) {
        context.dataStore.edit { preferences ->
            preferences[phone_key] = value
        }
    }

    suspend fun getToken() = withContext(IO){
        context.dataStore.data.first()[token_key]?:""
    }
    suspend fun getRefreshToken() = withContext(IO){
        context.dataStore.data.first()[refresh_token_key]?:""
    }
    suspend fun getPhone() = withContext(IO){
        context.dataStore.data.first()[phone_key]?:""
    }
}