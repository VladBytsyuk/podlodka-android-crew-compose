package com.vbytsyuk.paccomposeapp

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.vbytsyuk.paccomposeapp.ui.screens.DetailScreen
import com.vbytsyuk.paccomposeapp.ui.screens.MainScreen


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
                enterRoute(route = Routes.MAIN) {
                    MainScreen(
                        viewModel,
                        onDetailsClick = { sessionId ->
                            navHostController.navigate(Routes.sessionDetails(sessionId))
                        }
                    )
                }

                enterRoute(route = Routes.SESSION_DETAILS) { backStackEntry ->
                    val sessions = viewModel.sessions.value
                    val sessionId = backStackEntry.arguments?.getString(Routes.SESSION_ID)

                    DetailScreen(
                        viewModel,
                        session = sessions.find { it.id == sessionId } ?: sessions.random(),
                        onBackClick = { navHostController.popBackStack() }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.enterRoute(
    route: String,
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(route) { backStackEntry ->
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(initialAlpha = 0.3f, animationSpec = spring()),
        exit = fadeOut(),
        content = { content(backStackEntry) },
        initiallyVisible = false
    )
}


private object Routes {
    const val MAIN = "main"

    const val SESSION_ID = "sessionId"
    val SESSION_DETAILS = sessionDetails(sessionId = "{$SESSION_ID}")
    fun sessionDetails(sessionId: String) = "session_details/$sessionId"
}
