package fi.developer.kotlinmultiplatform.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WordOptionChip(
    option: String,
    isSelected: Boolean,
    isCorrectOption: Boolean,
    hasValidated: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val containerColor = when {
        hasValidated && isCorrectOption -> Color(0xFFC8E6C9)
        hasValidated && isSelected && !isCorrectOption -> Color(0xFFFFCDD2)
        isSelected -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surface
    }

    val contentColor = when {
        hasValidated && isCorrectOption -> Color(0xFF1B5E20)
        hasValidated && isSelected && !isCorrectOption -> Color(0xFFB71C1C)
        else -> MaterialTheme.colorScheme.onSurface
    }

    val borderColor = when {
        hasValidated && isCorrectOption -> Color(0xFF4CAF50)
        hasValidated && isSelected && !isCorrectOption -> Color(0xFFF44336)
        isSelected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.outline
    }

    Surface(
        onClick = { if (enabled) onClick() },
        color = containerColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(if (isSelected || hasValidated) 2.dp else 1.dp, borderColor),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Text(
            text = option,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (isSelected || (hasValidated && isCorrectOption)) FontWeight.Bold else FontWeight.Normal
        )
    }
}