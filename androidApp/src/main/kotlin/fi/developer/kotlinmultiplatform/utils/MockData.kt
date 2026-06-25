package fi.developer.kotlinmultiplatform.utils

import fi.developer.kotlinmultiplatform.model.word.WordItem

class MockData {
    companion object {
        val sampleWords = listOf(
            WordItem(1, "kuljettaja", "driver"),
            WordItem(2, "asiakas", "customer"),
            WordItem(3, "turvavyö", "seat belt"),
            WordItem(4, "ajoneuvo", "vehicle")
        )
    }
}