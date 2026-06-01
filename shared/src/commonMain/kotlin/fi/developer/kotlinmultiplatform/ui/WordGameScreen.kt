package fi.developer.kotlinmultiplatform.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fi.developer.kotlinmultiplatform.viewmodel.GameViewModel

@Composable
fun WordGameScreen(viewModel: GameViewModel) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Top Scoring Dashboard Panel Header Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Finnish KMP Game", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text("Score: ${state.score} / ${state.questionCount}", style = MaterialTheme.typography.bodyMedium)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Text Input", style = MaterialTheme.typography.labelSmall)
                    Spacer(modifier = Modifier.width(4.dp))
                    Switch(checked = state.isTextInputMode, onCheckedChange = { viewModel.toggleGameMode(it) })
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Active Card Target Prompts
        state.currentWord?.let { word ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = word.fin, // Finnish Word Suggested Prominently
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    if (state.isTextInputMode) {
                        OutlinedTextField(
                            value = state.typedInput,
                            onValueChange = { viewModel.updateTypedInput(it) },
                            label = { Text("English Translation") },
                            enabled = !state.hasValidatedTurn,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        state.multipleChoiceOptions.forEach { option ->
                            WordOptionChip(
                                option = option,
                                isSelected = state.selectedOption == option,
                                isCorrectOption = option.equals(word.eng, ignoreCase = true),
                                hasValidated = state.hasValidatedTurn,
                                enabled = !state.hasValidatedTurn,
                                onClick = { viewModel.selectOption(option) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Feedback Alert Box
            if (state.hasValidatedTurn) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (state.isCurrentAnswerCorrect) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                    )
                ) {
                    Text(
                        text = if (state.isCurrentAnswerCorrect) "🎉 Correct Choice!" else "❌ Incorrect. Answer: ${word.eng}",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                        color = if (state.isCurrentAnswerCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Action Processing Pad
            if (!state.hasValidatedTurn) {
                Button(
                    onClick = { viewModel.verifySelection { isCorrect -> /* Trigger native sounds here if desired */ } },
//                    enabled = !viewModel.isSelectionEmpty,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Verify Answer")
                }
            } else {
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = { viewModel.saveAndResetSession() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Finish Session 🛑")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { viewModel.generateNextRound() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Next Word →")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // History Tracker Drawer Logs Accordion
        OutlinedButton(onClick = { viewModel.toggleHistoryVisibility() }, modifier = Modifier.fillMaxWidth()) {
            Text(if (state.showHistory) "Hide Logs" else "View Session History")
        }

        AnimatedVisibility(visible = state.showHistory) {
            Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                if (state.historyLogs.isEmpty()) {
                    Text("No sessions saved yet.", modifier = Modifier.padding(16.dp))
                } else {
                    state.historyLogs.forEach { log ->
                        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                            Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Result: ${log.correctAnswers}/${log.totalQuestions}", fontWeight = FontWeight.Bold)
                                Text(log.gameMode, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}