package com.example.rakshaai.ui.navigation

import kotlinx.serialization.Serializable

// Type-safe Navigation Compose routes. Routes with arguments (incident detail, edit)
// are added in Milestone 2 as @Serializable data classes.

@Serializable
data object HomeRoute

@Serializable
data object IncidentsRoute

@Serializable
data object NewIncidentRoute

@Serializable
data object ContactsRoute

@Serializable
data object EmergencyRoute

@Serializable
data object AssistantRoute

@Serializable
data object SettingsRoute
