package fi.developer.kotlinmultiplatform.data.model.word

import kotlinx.serialization.Serializable

@Serializable
data class WordGameHistory(
    val testId: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val dateTime: Long,
    val gameMode: String
)