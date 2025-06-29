package com.semirsuljevic.api.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.semirsuljevic.MainViewModel


@Composable
fun AppNavigator(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    
    LaunchedEffect(Unit) {
        mainViewModel.setNavController(navController)
    }
    //until start destination is loaded - which is actually instant, don't show anything
    if(mainViewModel.startDestination != null) {
        NavHost(
            navController = navController,
            startDestination = mainViewModel.startDestination?.path ?: "",
        ) {
            //here we put nav graphs for all modules (or maybe even multiple per module)
            appNavGraph()
        }    
    }
    
    


}
