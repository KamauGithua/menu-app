package com.kamau.menuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.kamau.menuapp.model.FruitsData
import com.kamau.menuapp.ui.theme.MenuAppTheme
import com.kamau.menuapp.view.FruitGrid
import com.kamau.menuapp.view.FruitsDataDetails

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenuAppTheme {
                Surface {
                    NavigatePage()
                }
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun NavigatePage(){

    val navHostController = rememberNavController()


    NavHost(
        navController = navHostController,
        startDestination = "fruits_data"
    ){
        composable("fruits_data"){
            FruitGrid(navController = navHostController)
        }
        composable("grid_detail/{item}",
            arguments = listOf(
                navArgument("item"){
                    type = NavType.StringType
                }
            )
        ){navBackStackEntry ->

            navBackStackEntry.arguments?.getString("item")?.let { json->
                val item = Gson().fromJson(json, FruitsData::class.java)
                FruitsDataDetails(data = item)

            }
        }
    }



}


