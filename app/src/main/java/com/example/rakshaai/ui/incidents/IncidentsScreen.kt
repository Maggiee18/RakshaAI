package com.example.rakshaai.ui.incidents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rakshaai.R
import com.example.rakshaai.ui.components.EmptyStateCard
import com.example.rakshaai.ui.components.MilestoneNotice
import com.example.rakshaai.ui.components.RakshaTopAppBar

@Composable
fun IncidentsScreen(
    onCreateIncident: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { RakshaTopAppBar(title = stringResource(R.string.incidents_title)) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onCreateIncident,
                icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = null) },
                text = { Text(stringResource(R.string.incidents_new_report)) },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MilestoneNotice(text = stringResource(R.string.incidents_milestone_notice))
            EmptyStateCard(
                icon = Icons.Outlined.Description,
                title = stringResource(R.string.incidents_empty_title),
                message = stringResource(R.string.incidents_empty_message),
            )
        }
    }
}
