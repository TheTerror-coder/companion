package ft.project.companion.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext

val Context.companionDataStore: DataStore<Preferences> by preferencesDataStore("authDataStore")