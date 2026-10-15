package com.example.rakshaai.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rakshaai.BuildConfig
import com.example.rakshaai.R
import com.example.rakshaai.ui.components.RakshaTopAppBar

/**
 * Read only in Milestone 1. Every row describes the app's actual current behaviour.
 * Toggles (demo mode, cloud sync consent, analytics consent) are added with the features they control.
 */
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            RakshaTopAppBar(
                title = stringResource(R.string.settings_title),
                onNavigateBack = onNavigateBack,
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            SettingsSectionTitle(stringResource(R.string.settings_section_alerts))
            SettingsRow(
                icon = Icons.Outlined.NotificationsActive,
                title = stringResource(R.string.settings_alert_delivery),
                value = stringResource(R.string.settings_alert_delivery_value),
            )
            HorizontalDivider()

            SettingsSectionTitle(stringResource(R.string.settings_section_privacy))
            SettingsRow(
                icon = Icons.Outlined.Lock,
                title = stringResource(R.string.settings_storage),
                value = stringResource(R.string.settings_storage_value),
            )
            SettingsRow(
                icon = Icons.Outlined.Analytics,
                title = stringResource(R.string.settings_analytics),
                value = stringResource(R.string.settings_analytics_value),
            )
            HorizontalDivider()

            SettingsSectionTitle(stringResource(R.string.settings_section_about))
            SettingsRow(
                icon = Icons.Outlined.Info,
                title = stringResource(R.string.settings_version),
                value = BuildConfig.VERSION_NAME,
            )
            SettingsRow(
                icon = Icons.Outlined.Gavel,
                title = stringResource(R.string.settings_disclaimer),
                value = stringResource(R.string.settings_disclaimer_value),
            )
        }
    }
}

@Composable
private fun SettingsSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 4.dp)
            .semantics { heading() },
    )
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    title: String,
    value: String,
) {
    ListItem(
        leadingContent = { Icon(imageVector = icon, contentDescription = null) },
        headlineContent = { Text(title) },
        supportingContent = { Text(value) },
    )
}
