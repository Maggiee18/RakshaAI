package com.example.rakshaai.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SmartToy
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.rakshaai.R

/** Destinations shown in the bottom navigation bar. */
enum class TopLevelDestination(
    val route: Any,
    @StringRes val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    HOME(HomeRoute, R.string.nav_home, Icons.Filled.Home, Icons.Outlined.Home),
    INCIDENTS(IncidentsRoute, R.string.nav_incidents, Icons.Filled.Description, Icons.Outlined.Description),
    CONTACTS(ContactsRoute, R.string.nav_contacts, Icons.Filled.Contacts, Icons.Outlined.Contacts),
    ASSISTANT(AssistantRoute, R.string.nav_assistant, Icons.Filled.SmartToy, Icons.Outlined.SmartToy),
}

/** True when this destination, or one of its parents, is [destination]. */
fun NavDestination?.isTopLevel(destination: TopLevelDestination): Boolean {
    val current = this ?: return false
    return current.hierarchy.any { node ->
        when (destination) {
            TopLevelDestination.HOME -> node.hasRoute(HomeRoute::class)
            TopLevelDestination.INCIDENTS -> node.hasRoute(IncidentsRoute::class)
            TopLevelDestination.CONTACTS -> node.hasRoute(ContactsRoute::class)
            TopLevelDestination.ASSISTANT -> node.hasRoute(AssistantRoute::class)
        }
    }
}

/**
 * Switches tabs without stacking duplicates: pops back to the start destination,
 * saves the state of the tab being left and restores the state of the tab being opened.
 */
fun NavController.navigateToTopLevel(destination: TopLevelDestination) {
    navigate(destination.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
