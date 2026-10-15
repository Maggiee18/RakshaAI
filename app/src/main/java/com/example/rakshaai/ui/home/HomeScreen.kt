@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rakshaai.ui.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SmartToy
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rakshaai.R
import com.example.rakshaai.domain.model.Incident
import com.example.rakshaai.domain.model.TrustedContact
import com.example.rakshaai.ui.components.EmptyStateCard
import com.example.rakshaai.ui.components.SectionHeader
import com.example.rakshaai.ui.components.SosButton
import com.example.rakshaai.ui.incidents.labelRes
import com.example.rakshaai.ui.theme.RakshaAITheme
import com.example.rakshaai.util.formatTimestamp

/** Stateful entry point: reads state from [HomeViewModel] and forwards events up to navigation. */
@Composable
fun HomeScreen(
    onSosClick: () -> Unit,
    onCreateIncident: () -> Unit,
    onViewIncidents: () -> Unit,
    onManageContacts: () -> Unit,
    onOpenAssistant: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        uiState = uiState,
        onSosClick = onSosClick,
        onCreateIncident = onCreateIncident,
        onViewIncidents = onViewIncidents,
        onManageContacts = onManageContacts,
        onOpenAssistant = onOpenAssistant,
        onOpenSettings = onOpenSettings,
        modifier = modifier,
    )
}

/** Stateless dashboard, used directly by previews and later by UI tests. */
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onSosClick: () -> Unit,
    onCreateIncident: () -> Unit,
    onViewIncidents: () -> Unit,
    onManageContacts: () -> Unit,
    onOpenAssistant: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { HomeTitle() },
                actions = {
                    IconButton(onClick = onOpenSettings) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = stringResource(R.string.cd_open_settings),
                        )
                    }
                },
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyColumn(
                // Caps the width on tablets and landscape so cards stay readable.
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 640.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                item(key = "sos") {
                    SosHeroCard(onSosClick = onSosClick)
                }
                item(key = "quick_actions") {
                    QuickActionsSection(
                        onCreateIncident = onCreateIncident,
                        onManageContacts = onManageContacts,
                        onOpenAssistant = onOpenAssistant,
                        onEmergencyNumbers = onSosClick,
                    )
                }
                item(key = "contacts") {
                    TrustedContactsSection(
                        contacts = uiState.trustedContacts,
                        onManageContacts = onManageContacts,
                    )
                }
                item(key = "incidents") {
                    RecentIncidentsSection(
                        incidents = uiState.recentIncidents,
                        onCreateIncident = onCreateIncident,
                        onViewIncidents = onViewIncidents,
                    )
                }
                item(key = "assistant") {
                    AssistantCard(onOpenAssistant = onOpenAssistant)
                }
            }
        }
    }
}

@Composable
private fun HomeTitle() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Filled.Shield,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp),
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(R.string.app_tagline),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun SosHeroCard(onSosClick: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.home_sos_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.home_sos_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(8.dp))
            SosButton(onClick = onSosClick)
            Text(
                text = stringResource(R.string.home_sos_footer),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun QuickActionsSection(
    onCreateIncident: () -> Unit,
    onManageContacts: () -> Unit,
    onOpenAssistant: () -> Unit,
    onEmergencyNumbers: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionHeader(title = stringResource(R.string.home_quick_actions))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard(
                label = stringResource(R.string.home_action_report),
                icon = Icons.Outlined.Description,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                onClick = onCreateIncident,
                modifier = Modifier.weight(1f),
            )
            QuickActionCard(
                label = stringResource(R.string.home_action_contacts),
                icon = Icons.Outlined.PersonAdd,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                onClick = onManageContacts,
                modifier = Modifier.weight(1f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard(
                label = stringResource(R.string.home_action_assistant),
                icon = Icons.Outlined.SmartToy,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                onClick = onOpenAssistant,
                modifier = Modifier.weight(1f),
            )
            QuickActionCard(
                label = stringResource(R.string.home_action_emergency_numbers),
                icon = Icons.Outlined.Phone,
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                onClick = onEmergencyNumbers,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun QuickActionCard(
    label: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.heightIn(min = 96.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun TrustedContactsSection(
    contacts: List<TrustedContact>,
    onManageContacts: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionHeader(
            title = stringResource(R.string.home_contacts_title),
            actionLabel = stringResource(R.string.home_contacts_manage),
            onAction = onManageContacts,
        )
        if (contacts.isEmpty()) {
            EmptyStateCard(
                icon = Icons.Outlined.People,
                title = stringResource(R.string.home_contacts_empty_title),
                message = stringResource(R.string.home_contacts_empty_message),
                actionLabel = stringResource(R.string.home_contacts_empty_action),
                onAction = onManageContacts,
            )
        } else {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                contacts.forEach { contact ->
                    AssistChip(
                        onClick = onManageContacts,
                        label = { Text(contact.name) },
                        leadingIcon = {
                            Icon(
                                imageVector = if (contact.isEmergencyContact) Icons.Filled.Star else Icons.Outlined.Person,
                                contentDescription = if (contact.isEmergencyContact) {
                                    stringResource(R.string.cd_emergency_contact)
                                } else {
                                    null
                                },
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentIncidentsSection(
    incidents: List<Incident>,
    onCreateIncident: () -> Unit,
    onViewIncidents: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionHeader(
            title = stringResource(R.string.home_incidents_title),
            actionLabel = stringResource(R.string.home_incidents_view_all),
            onAction = onViewIncidents,
        )
        if (incidents.isEmpty()) {
            EmptyStateCard(
                icon = Icons.Outlined.Description,
                title = stringResource(R.string.home_incidents_empty_title),
                message = stringResource(R.string.home_incidents_empty_message),
                actionLabel = stringResource(R.string.home_incidents_empty_action),
                onAction = onCreateIncident,
            )
        } else {
            incidents.forEach { incident ->
                OutlinedCard(
                    onClick = onViewIncidents,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    ListItem(
                        headlineContent = {
                            Text(incident.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        },
                        supportingContent = {
                            Text(
                                text = stringResource(incident.category.labelRes()) +
                                    " · " + formatTimestamp(incident.timestampMillis),
                            )
                        },
                        trailingContent = {
                            Text(
                                text = stringResource(incident.status.labelRes()),
                                style = MaterialTheme.typography.labelMedium,
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun AssistantCard(onOpenAssistant: () -> Unit) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.SmartToy,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = stringResource(R.string.home_assistant_title),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.home_assistant_message),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(16.dp))
            FilledTonalButton(onClick = onOpenAssistant) {
                Text(stringResource(R.string.home_assistant_action))
            }
        }
    }
}

@Preview(name = "Home light", showBackground = true)
@Composable
private fun HomeLightPreview() {
    RakshaAITheme(darkTheme = false) {
        HomeContent(HomeUiState(), {}, {}, {}, {}, {}, {})
    }
}

@Preview(name = "Home dark", showBackground = true)
@Composable
private fun HomeDarkPreview() {
    RakshaAITheme(darkTheme = true) {
        HomeContent(HomeUiState(), {}, {}, {}, {}, {}, {})
    }
}
