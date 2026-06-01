package fi.developer.kotlinmultiplatform.viewmodel

import fi.developer.kotlinmultiplatform.model.GameState
import fi.developer.kotlinmultiplatform.model.WordGameHistory
import fi.developer.kotlinmultiplatform.model.WordItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(initialWords: List<WordItem>) {
    private val _uiState = MutableStateFlow(
        GameState(
            wordPool = initialWords,
            currentTestId = "session_${ClockSystem.now()}"
        )
    )
    val uiState: StateFlow<GameState> = _uiState.asStateFlow()

    init {
        generateNextRound()
    }

    fun toggleGameMode(textMode: Boolean) {
        _uiState.update { it.copy(isTextInputMode = textMode) }
        generateNextRound()
    }

    fun updateTypedInput(input: String) {
        _uiState.update { it.copy(typedInput = input) }
    }

    fun selectOption(option: String) {
        _uiState.update { it.copy(selectedOption = option) }
    }

    fun toggleHistoryVisibility() {
        _uiState.update { it.copy(showHistory = !it.showHistory) }
    }

    fun verifySelection(onAnswerEvaluated: (Boolean) -> Unit) {
        val state = _uiState.value
        val target = state.currentWord ?: return
        val userAnswer = if (state.isTextInputMode) state.typedInput.trim() else state.selectedOption

        if (userAnswer.isEmpty()) return

        val isCorrect = userAnswer.equals(target.eng, ignoreCase = true)
        onAnswerEvaluated(isCorrect) // Callback to let UI play platform audio if desired

        _uiState.update { old ->
            old.copy(
                isCurrentAnswerCorrect = isCorrect,
                score = if (isCorrect) old.score + 1 else old.score,
                questionCount = old.questionCount + 1,
                hasValidatedTurn = true
            )
        }
    }

    fun generateNextRound() {
        val pool = _uiState.value.wordPool
        if (pool.isEmpty()) return

        val target = pool.random()
        val distractors = pool.filter { it.id != target.id }.shuffled().take(2).map { it.eng }
        val options = (distractors + target.eng).shuffled()

        _uiState.update { old ->
            old.copy(
                currentWord = target,
                multipleChoiceOptions = options,
                typedInput = "",
                selectedOption = "",
                hasValidatedTurn = false
            )
        }
    }

    fun saveAndResetSession() {
        _uiState.update { old ->
            val newLog = WordGameHistory(
                testId = old.currentTestId.take(8),
                totalQuestions = old.questionCount,
                correctAnswers = old.score,
                dateTime = ClockSystem.now(),
                gameMode = if (old.isTextInputMode) "Text Input" else "Multiple Choice"
            )
            old.copy(
                historyLogs = old.historyLogs + newLog,
                score = 0,
                questionCount = 0,
                currentTestId = "session_${ClockSystem.now()}"
            )
        }
        generateNextRound()
    }
}

// Minimal cross-platform placeholder clock timestamp generator
object ClockSystem {
    fun now(): Long = 1717270000000L // Implement via expect/actual or simple systemic wrapper if needed
}