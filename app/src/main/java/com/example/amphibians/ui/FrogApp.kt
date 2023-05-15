package com.example.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.FrogViewModel
import com.example.amphibians.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrogApp(modifier:Modifier = Modifier){
    Scaffold(modifier = modifier.fillMaxSize(),
    topBar = {},
    ){
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it),
        color = MaterialTheme.colorScheme.background) {
            val frogViewModel: FrogViewModel = viewModel(factory = FrogViewModel.Factory)
            HomeScreen(frogUiState = frogViewModel.frogUiState, retryAction = frogViewModel::getFrogData)
        }
    }
}