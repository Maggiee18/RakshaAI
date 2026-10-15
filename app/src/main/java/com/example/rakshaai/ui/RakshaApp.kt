package com.example.rakshaai.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rakshaai.ui.navigation.RakshaNavHost
import com.example.rakshaai.ui.navigation.TopLevelDestination
import com.example.rakshaai.ui.navigation.isTopLevel
import com.example.rakshaai.ui.navigation.navigateToTopLevel

/**
 * Root composable. Owns the NavController and the bottom navigation bar.
 * The bottom bar is visible on the four top-level tabs and hidden on detail screens
 * such as Emergency and Settings, which use a back arrow instead.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RakshaApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val showBottomBar = currentDestination == null ||
        TopLevelDestination.entries.any { currentDestination.isTopLevel(it) }

    Scaffold(
        // Each screen draws its own top bar and handles the status bar inset itself.
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                RakshaBottomBar(
                    currentDestination = currentDestination,
                    onNavigate = { navController.navigateToTopLevel(it) },
                )
            }
        },
    ) { innerPadding ->
        RakshaNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                // Prevents screens from adding the navigation bar inset a second time.
                .consumeWindowInsets(innerPadding),
        )
    }
}

@Composable
private fun RakshaBottomBar(
    currentDestination: NavDestination?,
    onNavigate: (TopLevelDestination) -> Unit,
) {
    NavigationBar {
        TopLevelDestination.entries.forEach { destination ->
            val selected = currentDestination.isTopLevel(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { if (!selected) onNavigate(destination) },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.labelRes)) },
            )
        }
    }
}
