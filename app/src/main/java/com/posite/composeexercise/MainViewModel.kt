package com.posite.composeexercise

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _height = mutableStateOf("")
    val height: State<String> = _height

    private val _weight = mutableStateOf("")
    val weight: State<String> = _weight

    private val _bmi = mutableStateOf(0.0)
    val bmi: State<Double> = _bmi

    fun setHeight(height: String) {
        _height.value = height
    }

    fun setWeight(weight: String) {
        _weight.value = weight
        Log.d("set", weight)
    }

    fun calculateBmi() {
        Log.d("set", height.value)
        Log.d("set", weight.value)
        _bmi.value = _weight.value.toDouble() / (_height.value.toDouble() / 100.0).pow(2)
    }
}