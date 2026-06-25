package fi.developer.kotlinmultiplatform.model.word

data class GameState(
    val wordPool: List<WordItem> = emptyList(),
    val currentWord: WordItem? = null,
    val multipleChoiceOptions: List<String> = emptyList(),
    val isTextInputMode: Boolean = false,
    val typedInput: String = "",
    val selectedOption: String = "",
    val hasValidatedTurn: Boolean = false,
    val isCurrentAnswerCorrect: Boolean = false,
    val score: Int = 0,
    val questionCount: Int = 0,
    val showHistory: Boolean = false,
    val historyLogs: List<WordGameHistory> = emptyList(),
    val currentTestId: String = "",
    val isLoading: Boolean = true // Added flag to show a loading indicator in UI
)