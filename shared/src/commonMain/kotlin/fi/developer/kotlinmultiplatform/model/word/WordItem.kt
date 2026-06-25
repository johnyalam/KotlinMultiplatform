package fi.developer.kotlinmultiplatform.model.word

import kotlinx.serialization.Serializable

@Serializable
data class WordItem(
    val id: Int,
    val fin: String,
    val eng: String
)