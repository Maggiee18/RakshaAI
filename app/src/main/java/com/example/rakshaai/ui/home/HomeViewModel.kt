package com.example.rakshaai.ui.home

import androidx.lifecycle.ViewModel
import com.example.rakshaai.domain.model.Incident
import com.example.rakshaai.domain.model.TrustedContact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val trustedContacts: List<TrustedContact> = emptyList(),
    val recentIncidents: List<Incident> = emptyList(),
)

/**
 * Milestone 1: exposes an empty state because no data source exists yet, so the
 * dashboard shows its real empty states rather than fake records.
 * Milestone 2 injects the incident repository and combines its Room backed Flow here.
 */
class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
