package com.example.rakshaai.ui.incidents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rakshaai.R
import com.example.rakshaai.ui.components.MilestoneNotice
import com.example.rakshaai.ui.components.RakshaTopAppBar

/** Destination for "create report". The real form replaces this content in Milestone 2. */
@Composable
fun NewIncidentScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            RakshaTopAppBar(
                title = stringResource(R.string.incident_editor_title),
                onNavigateBack = onNavigateBack,
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            MilestoneNotice(text = stringResource(R.string.incident_editor_milestone_notice))
        }
    }
}
