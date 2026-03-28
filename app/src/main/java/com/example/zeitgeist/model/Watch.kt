package com.example.zeitgeist.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Watch(
    val id: String = UUID.randomUUID().toString(),
    var modelName: String,
    var brand: String,
    var reference: String?,
    var imagePath: String = NO_IMAGE
) {
    companion object {
        const val NO_IMAGE = "placeholder"
    }

}
