package com.example.zeitgeist.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.zeitgeist.model.Watch
import kotlinx.coroutines.flow.Flow

// Extension property on Context — creates/accesses the file "watches.json"
val Context.watchDataStore: DataStore<List<Watch>> by dataStore(
    fileName = "watches.json",
    serializer = WatchListSerializer
)

class WatchListRepository(private val context: Context) {

    val watches: Flow<List<Watch>> = context.watchDataStore.data

    suspend fun addWatch(watch: Watch) {
        context.watchDataStore.updateData { current -> current + watch }
    }

    suspend fun removeWatch(id: String) {
        context.watchDataStore.updateData { current -> current.filter { it.id != id } }
    }
}
