package com.posite.composeexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "first") {
                composable("first") {
                    FirstScreen(navController)
                }
                composable("second") {
                    SecondScreen(navController)
                }
                composable("third/{value}") {
                    ThirdScreen(
                        controller = navController,
                        value = it.arguments?.getString("value") ?: ""
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(controller: NavController) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "첫 화면")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            controller.navigate("second")
        }) {
            Text("두번째")
        }

        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = value, onValueChange = setValue)

        Button(onClick = {
            if (value.isNotEmpty()) {
                controller.navigate("third/$value")
            }
        }) {
            Text("세번째")
        }
    }
}

@Composable
fun SecondScreen(controller: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "두번째 화면")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            controller.popBackStack()
        }) {
            Text("뒤로가기")
        }
    }
}

@Composable
fun ThirdScreen(controller: NavController, value: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "세번째 화면")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "value: $value")
        Button(onClick = {
            controller.popBackStack()
        }) {
            Text("뒤로가기")
        }
    }
}

