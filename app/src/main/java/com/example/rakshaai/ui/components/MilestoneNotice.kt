package com.example.rakshaai.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rakshaai.R

/**
 * Clearly labels functionality that is not built yet, so the UI never implies
 * that something works when it does not.
 */
@Composable
fun MilestoneNotice(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(imageVector = Icons.Outlined.Construction, contentDescription = null)
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = stringResource(R.string.milestone_label),
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(text = text, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
