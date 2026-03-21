package com.example.zeitgeist.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Watch(
    val id: String = UUID.randomUUID().toString(),
    var modelName: String,
    var brand: String,
    var reference: String?,
) {

}
