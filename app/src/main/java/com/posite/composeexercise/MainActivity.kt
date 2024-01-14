package com.posite.composeexercise

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    Home(navController = navController)
                }
                composable("result") {
                    Result(navController = navController)
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Home(
        navController: NavHostController,
    ) {

        Scaffold(
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text("비만도 계산기")
                        }
                    )
                }
            },
        ) { paddings ->
            Column(
                modifier = Modifier.padding(
                    16.dp,
                    paddings.calculateTopPadding(),
                    16.dp,
                    paddings.calculateTopPadding()
                )
            ) {
                OutlinedTextField(
                    value = mainViewModel.height.value,
                    onValueChange = mainViewModel::setHeight,
                    label = { Text("키") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = mainViewModel.weight.value,
                    onValueChange = mainViewModel::setWeight,
                    label = { Text("몸무게") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        d("set", mainViewModel.height.value)
                        d("set", mainViewModel.weight.value)
                        if (mainViewModel.height.value.isNotEmpty() && mainViewModel.weight.value.isNotEmpty()) {
                            mainViewModel.calculateBmi()
                            navController.navigate("result")
                        }

                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("결과")
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    fun Result(
        navController: NavHostController
    ) {
        Log.d("set", mainViewModel.height.value)
        Log.d("set", mainViewModel.weight.value)
        Log.d("bmi", mainViewModel.bmi.value.toString())
        val text = when {
            mainViewModel.bmi.value >= 35.0 -> "고도 비만"
            mainViewModel.bmi.value >= 30.0 -> "2단계 비만"
            mainViewModel.bmi.value >= 25.0 -> "1단계 비만"
            mainViewModel.bmi.value >= 23.0 -> "과체중"
            mainViewModel.bmi.value >= 18.5 -> "정상"
            else -> "저체중"
        }

        val img = when {
            mainViewModel.bmi.value >= 23.0 -> R.drawable.sentiment_very_dissatisfied
            mainViewModel.bmi.value >= 18.5 -> R.drawable.sentiment_satisfied
            else -> R.drawable.sentiment_dissatisfied
        }
        Scaffold(
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text("비만도 계산기")
                        },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "home",
                                modifier = Modifier.clickable {
                                    navController.popBackStack()
                                })
                        }
                    )
                }
            },
        ) { paddings ->
            Column(
                modifier = Modifier
                    .padding(
                        16.dp,
                        paddings.calculateTopPadding(),
                        16.dp,
                        paddings.calculateTopPadding()
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}



