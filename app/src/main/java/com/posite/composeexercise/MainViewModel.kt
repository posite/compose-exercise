package com.posite.composeexercise

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _data = mutableStateOf("Hello")
    val data: State<String> = _data

    fun changeData() {
        _data.value = "World!"
    }
}