package com.vbytsyuk.paccomposeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vbytsyuk.paccomposeapp.views.DetailScreen
import com.vbytsyuk.paccomposeapp.views.MainScreen


@Composable
fun NavigationHost(navHostController: NavHostController, viewModel: MainViewModel) =
    NavHost(navHostController, startDestination = "main") {
        composable(route = NavigationRoutes.MAIN) { MainScreen(viewModel, navHostController) }
        composable(route = NavigationRoutes.sessionDetails(NavigationRoutes.SESSION_DETAILS_ARG)) { backStackEntry ->
            val sessions = viewModel.sessions.value
            val sessionId = backStackEntry.arguments?.getString("sessionId")
            DetailScreen(session = sessions.find { it.id == sessionId } ?: sessions.random())
        }
    }

object NavigationRoutes {
    const val MAIN = "main"
    fun sessionDetails(sessionId: String) = "session_details/$sessionId"
    const val SESSION_DETAILS_ARG = "{sessionId}"
}
