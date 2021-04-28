package com.vbytsyuk.paccomposeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.vbytsyuk.paccomposeapp.views.DetailScreen
import com.vbytsyuk.paccomposeapp.views.MainScreen


@Composable
fun NavigationHost(
    navHostController: NavHostController,
    viewModel: AppViewModel = viewModel()
) {
    val theme by viewModel.theme.collectAsState()

    theme {
        Box(
            modifier = Modifier.background(theme.colors.background)
        ) {
            NavHost(navHostController, startDestination = "main") {
                composable(route = Routes.MAIN) {
                    MainScreen(
                        viewModel,
                        onDetailsClick = { sessionId ->
                            navHostController.navigate(Routes.sessionDetails(sessionId))
                        }
                    )
                }
                composable(route = Routes.SESSION_DETAILS) { backStackEntry ->
                    val sessions = viewModel.sessions.value
                    val sessionId = backStackEntry.arguments?.getString(Routes.SESSION_ID)
                    DetailScreen(
                        viewModel,
                        session = sessions.find { it.id == sessionId } ?: sessions.random(),
                        onBackClick = {
                            navHostController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

private object Routes {
    const val MAIN = "main"
    const val SESSION_ID = "sessionId"
    val SESSION_DETAILS = sessionDetails(sessionId = "{$SESSION_ID}")
    fun sessionDetails(sessionId: String) = "session_details/$sessionId"
}
