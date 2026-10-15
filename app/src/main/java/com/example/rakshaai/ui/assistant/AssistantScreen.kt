package com.example.rakshaai.ui.assistant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.example.rakshaai.R
import com.example.rakshaai.ui.components.ImmediateDangerCard
import com.example.rakshaai.ui.components.MilestoneNotice
import com.example.rakshaai.ui.components.RakshaTopAppBar
import com.example.rakshaai.util.EmergencyResources
import com.example.rakshaai.util.openDialer
import kotlinx.coroutines.launch

/**
 * The emergency banner is always shown first so that the AI assistant is never
 * the primary route to help when someone is in danger.
 */
@Composable
fun AssistantScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val noDialerMessage = stringResource(R.string.error_no_dialer)

    Scaffold(
        modifier = modifier,
        topBar = { RakshaTopAppBar(title = stringResource(R.string.assistant_title)) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ImmediateDangerCard(
                onCallEmergency = {
                    if (!context.openDialer(EmergencyResources.PRIMARY_EMERGENCY_NUMBER)) {
                        scope.launch { snackbarHostState.showSnackbar(noDialerMessage) }
                    }
                },
            )
            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = stringResource(R.string.assistant_disclaimer_title),
                            style = MaterialTheme.typography.titleSmall,
                        )
                        Text(
                            text = stringResource(R.string.assistant_disclaimer_message),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
            MilestoneNotice(text = stringResource(R.string.assistant_milestone_notice))
        }
    }
}
