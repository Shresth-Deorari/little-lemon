package com.example.littlelemon.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val PREFERENCE_NAME = "little_lemon_preference"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreRepository(private val context: Context) {
    companion object{
        val FIRST_NAME = stringPreferencesKey("FIRST_NAME")
        val LAST_NAME = stringPreferencesKey("LAST_NAME")
        val EMAIL = stringPreferencesKey("EMAIL")
    }

    suspend fun saveToDataStore(userDetails: UserDetails){
        try{
            context.dataStore.edit {
                it[FIRST_NAME] = userDetails.FIRST_NAME
                it[LAST_NAME] = userDetails.LAST_NAME
                it[EMAIL] = userDetails.EMAIL
                Log.d("DataStoreRepository","FirstName : ${it[FIRST_NAME]}")
                Log.d("DataStoreRepository","LastName : ${it[LAST_NAME]}")
                Log.d("DataStoreRepository","Email : ${it[EMAIL]}")
            }
        }
        catch (e :Exception){
            Log.d("DataStoreRepository", "Failed to save")
        }
    }

    fun getFirstNameFromDataStore() : Flow<String> {
        return context.dataStore.data.map{preferences->
            preferences[FIRST_NAME]?:""
        }
    }
    fun getLastNameFromDataStore() : Flow<String> {
        return context.dataStore.data.map{preferences->
            preferences[LAST_NAME]?:""
        }
    }

    fun getEmailFromDataStore() : Flow<String> {
        return context.dataStore.data.map{preferences->
            preferences[EMAIL]?:""
        }
    }

    suspend fun clearDataStore(){
        context.dataStore.edit { preferences->
            preferences.clear()
        }
    }

}

data class UserDetails(
    val FIRST_NAME : String,
    val LAST_NAME : String,
    val EMAIL : String
)