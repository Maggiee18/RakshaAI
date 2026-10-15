package com.example.rakshaai.ui.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rakshaai.R
import com.example.rakshaai.ui.components.EmptyStateCard
import com.example.rakshaai.ui.components.MilestoneNotice
import com.example.rakshaai.ui.components.RakshaTopAppBar

@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { RakshaTopAppBar(title = stringResource(R.string.contacts_title)) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MilestoneNotice(text = stringResource(R.string.contacts_milestone_notice))
            EmptyStateCard(
                icon = Icons.Outlined.People,
                title = stringResource(R.string.contacts_empty_title),
                message = stringResource(R.string.contacts_empty_message),
            )
        }
    }
}
