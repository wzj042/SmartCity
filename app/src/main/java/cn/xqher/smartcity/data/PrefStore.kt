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
        private val BASE_URL_KEY = stringPreferencesKey(" base_url")
    }

    data class PrefData(
        val firstInstall: String = "",
        val baseUrl: String = "",
    )

    val getFirstInstall: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[FIRST_INSTALL_KEY] ?: ""
    }
    val getBaseUrl: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[BASE_URL_KEY] ?: ""
    }


    suspend fun writePrefData(
        prefData: PrefData
    ) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_INSTALL_KEY] = prefData.firstInstall
            preferences[BASE_URL_KEY] = prefData.baseUrl
        }
    }
}