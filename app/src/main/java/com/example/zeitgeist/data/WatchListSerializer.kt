package com.example.zeitgeist.data

import androidx.datastore.core.Serializer
import com.example.zeitgeist.model.Watch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object WatchListSerializer : Serializer<List<Watch>> {
    override val defaultValue: List<Watch> = emptyList()

    override suspend fun readFrom(input: InputStream): List<Watch> {
        return try {
            Json.decodeFromString(
                ListSerializer(Watch.serializer()),
                input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: List<Watch>, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(ListSerializer(Watch.serializer()), t).encodeToByteArray()
            )
        }
    }
}