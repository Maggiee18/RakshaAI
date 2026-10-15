package com.example.rakshaai.ui.emergency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rakshaai.R
import com.example.rakshaai.ui.components.MilestoneNotice
import com.example.rakshaai.ui.components.RakshaTopAppBar
import com.example.rakshaai.ui.components.SectionHeader
import com.example.rakshaai.ui.theme.OnSosRed
import com.example.rakshaai.ui.theme.SosRed
import com.example.rakshaai.util.EmergencyResource
import com.example.rakshaai.util.EmergencyResources
import com.example.rakshaai.util.openDialer
import kotlinx.coroutines.launch

/**
 * Milestone 1 emergency screen.
 * Working now: opening the phone dialer for public helplines (the user still presses call).
 * Not yet built: alerting trusted contacts. The screen states this plainly instead of simulating it.
 */
@Composable
fun EmergencyScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val noDialerMessage = stringResource(R.string.error_no_dialer)

    val dial: (String) -> Unit = { number ->
        if (!context.openDialer(number)) {
            scope.launch { snackbarHostState.showSnackbar(noDialerMessage) }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            RakshaTopAppBar(
                title = stringResource(R.string.emergency_title),
                onNavigateBack = onNavigateBack,
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 640.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item(key = "workflow") { AlertWorkflowStatusCard() }
                item(key = "numbers_header") {
                    Column {
                        Spacer(Modifier.height(8.dp))
                        SectionHeader(title = stringResource(R.string.emergency_numbers_title))
                        Text(
                            text = stringResource(R.string.emergency_numbers_disclaimer),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                items(items = EmergencyResources.india, key = { it.number }) { resource ->
                    EmergencyNumberCard(resource = resource, onDial = { dial(resource.number) })
                }
            }
        }
    }
}

@Composable
private fun AlertWorkflowStatusCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = stringResource(R.string.emergency_workflow_title),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(R.string.emergency_workflow_status),
                style = MaterialTheme.typography.bodyMedium,
            )
            MilestoneNotice(text = stringResource(R.string.emergency_workflow_milestone_notice))
        }
    }
}

@Composable
private fun EmergencyNumberCard(
    resource: EmergencyResource,
    onDial: () -> Unit,
) {
    val dialDescription = stringResource(R.string.cd_dial_number, resource.number)
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        ListItem(
            headlineContent = {
                Text(
                    text = resource.number + "  " + stringResource(resource.nameRes),
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            supportingContent = { Text(stringResource(resource.descriptionRes)) },
            trailingContent = {
                Button(
                    onClick = onDial,
                    modifier = Modifier.semantics { contentDescription = dialDescription },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SosRed,
                        contentColor = OnSosRed,
                    ),
                ) {
                    Icon(imageVector = Icons.Filled.Call, contentDescription = null)
                    Text(
                        text = stringResource(R.string.emergency_dial),
                        modifier = Modifier.padding(start = 6.dp),
                    )
                }
            },
        )
    }
}
