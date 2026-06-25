package fi.developer.kotlinmultiplatform.viewmodel

import fi.developer.kotlinmultiplatform.model.word.GameState
import fi.developer.kotlinmultiplatform.model.word.WordGameHistory
import fi.developer.kotlinmultiplatform.model.word.WordItem
import kotlinmultiplatform.shared.generated.resources.Res
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class GameViewModel() { // Removed 'initialWords' from the constructor parameter
    private val _uiState = MutableStateFlow(
        GameState(currentTestId = "session_${ClockSystem.now()}")
    )
    val uiState: StateFlow<GameState> = _uiState.asStateFlow()

    init {
        loadWordsAsset()
    }

    private fun loadWordsAsset() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bytes = Res.readBytes("files/words.json")
                val jsonString = bytes.decodeToString()
                val parsedPool = Json.decodeFromString<List<WordItem>>(jsonString)

                _uiState.update { old ->
                    old.copy(
                        wordPool = parsedPool,
                        isLoading = false
                    )
                }

                generateNextRound()
            } catch (e: Exception) {
                println("KMP Asset Parsing Error: ${e.message}")
            }
        }
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

    val isSelectionEmpty: Boolean
        get() = if (_uiState.value.isTextInputMode) {
            _uiState.value.typedInput.trim().isEmpty()
        } else {
            _uiState.value.selectedOption.isEmpty()
        }

    fun verifySelection(onAnswerEvaluated: (Boolean) -> Unit) {
        val state = _uiState.value
        val target = state.currentWord ?: return
        val userAnswer = if (state.isTextInputMode) state.typedInput.trim() else state.selectedOption

        if (userAnswer.isEmpty()) return

        val isCorrect = userAnswer.equals(target.eng, ignoreCase = true)
        onAnswerEvaluated(isCorrect)

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

        // Take 2 distinct wrong answers as distractors
        val distractors = pool
            .filter { it.id != target.id }
            .shuffled()
            .take(2)
            .map { it.eng }

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

object ClockSystem {
    fun now(): Long = 1717270000000L
}