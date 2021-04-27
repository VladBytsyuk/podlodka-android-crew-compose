package com.vbytsyuk.paccomposeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.vbytsyuk.paccomposeapp.views.DetailScreen
import com.vbytsyuk.paccomposeapp.views.MainScreen


@Composable
fun NavigationHost(navHostController: NavHostController, viewModel: MainViewModel) =
    NavHost(navHostController, startDestination = "main") {
        composable(route = NavigationRoutes.MAIN) { MainScreen(viewModel, navHostController) }
        composable(route = NavigationRoutes.SESSION_DETAILS) { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId")
            DetailScreen(
                session = MockSessions.find { it.id == sessionId } ?: MockSessions.random()
            )
        }
    }

object NavigationRoutes {
    const val MAIN = "main"
    const val SESSION_DETAILS = "session_details/{sessionId}"
}
