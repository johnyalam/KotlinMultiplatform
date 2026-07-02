package fi.developer.kotlinmultiplatform.data.model.word

import kotlinx.serialization.Serializable

@Serializable
data class WordItem(
    val id: Int,
    val fin: String,
    val eng: String
)