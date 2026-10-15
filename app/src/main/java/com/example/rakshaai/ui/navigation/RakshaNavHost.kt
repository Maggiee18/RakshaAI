package com.example.rakshaai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rakshaai.ui.assistant.AssistantScreen
import com.example.rakshaai.ui.contacts.ContactsScreen
import com.example.rakshaai.ui.emergency.EmergencyScreen
import com.example.rakshaai.ui.home.HomeScreen
import com.example.rakshaai.ui.incidents.IncidentsScreen
import com.example.rakshaai.ui.incidents.NewIncidentScreen
import com.example.rakshaai.ui.settings.SettingsScreen

@Composable
fun RakshaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onSosClick = { navController.navigate(EmergencyRoute) },
                onCreateIncident = { navController.navigate(NewIncidentRoute) },
                onViewIncidents = { navController.navigateToTopLevel(TopLevelDestination.INCIDENTS) },
                onManageContacts = { navController.navigateToTopLevel(TopLevelDestination.CONTACTS) },
                onOpenAssistant = { navController.navigateToTopLevel(TopLevelDestination.ASSISTANT) },
                onOpenSettings = { navController.navigate(SettingsRoute) },
            )
        }
        composable<IncidentsRoute> {
            IncidentsScreen(
                onCreateIncident = { navController.navigate(NewIncidentRoute) },
            )
        }
        composable<NewIncidentRoute> {
            NewIncidentScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable<ContactsRoute> {
            ContactsScreen()
        }
        composable<EmergencyRoute> {
            EmergencyScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable<AssistantRoute> {
            AssistantScreen()
        }
        composable<SettingsRoute> {
            SettingsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
