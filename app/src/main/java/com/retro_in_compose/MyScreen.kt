package com.retro_in_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyScreen(viewModel: MyViewModel = viewModel()) {
    val dataState by viewModel.dataState.collectAsState()

    when (dataState) {
        is DataState.Loading -> {
            // Loading UI
            Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }

        }
        is DataState.Success -> {
            // Success UI
            val data = (dataState as DataState.Success).data
            // Display the data
            LazyColumn {
              items(data) {item ->
                  Text(text = item.name)
              }
            }

        }
        is DataState.Error -> {
            // Error UI
            val error = (dataState as DataState.Error).exception
            // Handle the error
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Error: ${error.message}")
                Button(onClick = { viewModel.fetchData() }) {
                    Text("Retry")
                }
            }

        }
    }
}

