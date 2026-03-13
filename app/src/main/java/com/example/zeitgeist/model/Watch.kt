package com.example.zeitgeist.model

import java.util.UUID

data class Watch(
    val id: UUID = UUID.randomUUID(),
    var modelName: String,
    var brand: String,
    var reference: String?,
) {

}
