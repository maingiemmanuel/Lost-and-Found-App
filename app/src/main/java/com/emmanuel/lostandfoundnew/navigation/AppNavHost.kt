package com.emmanuel.lostandfoundnew.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emmanuel.lostandfoundnew.ui.theme.screens.add.AddScreen
import com.emmanuel.lostandfoundnew.ui.theme.screens.claim.LostItemScreen
import com.emmanuel.lostandfoundnew.ui.theme.screens.claim2.FoundItemScreen
import com.emmanuel.lostandfoundnew.ui.theme.screens.home.HomeScreen
import com.emmanuel.lostandfoundnew.ui.theme.screens.login.LoginScreen
import com.emmanuel.lostandfoundnew.ui.theme.screens.register.RegisterScreen
import com.emmanuel.lostandfoundnew.ui.theme.screens.report.ReportScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination:String = ROUTE_LOGIN) {
    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_CLAIM) {
            LostItemScreen(navController)
        }
        composable(ROUTE_REPORT) {
            ReportScreen(navController)
        }
        composable(ROUTE_CLAIM2) {
            FoundItemScreen(navController)
        }
        composable(ROUTE_ADD) {
            AddScreen(navController)
        }

    }




}