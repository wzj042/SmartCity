package cn.xqher.smartcity.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefStore (private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val FIRST_INSTALL_KEY = stringPreferencesKey("first_install")
        private val BASE_URL_KEY = stringPreferencesKey("base_url")
    }


    val getFirstInstall: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[FIRST_INSTALL_KEY] ?: ""
    }
    val getBaseUrl: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[BASE_URL_KEY] ?: ""
    }


    suspend fun setFirstInstall(
        firstInstall: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_INSTALL_KEY] = firstInstall
        }
    }
    suspend fun setBaseUrl(
        baseUrl: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[BASE_URL_KEY] = baseUrl
        }
    }
}