package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian

@Composable
fun HomeScreen(
    frogUiState: FrogUiState,
    retryAction: ()->Unit,
    modifier: Modifier = Modifier
){
    when(frogUiState){
        is FrogUiState.Loading -> LoadingScreen(modifier)
        is FrogUiState.Success -> FrogGrid(frogData = frogUiState.data, modifier = modifier)
        is FrogUiState.Error -> ErrorScreen(retryAction, modifier)
    }

}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Box(

        modifier = modifier.fillMaxSize()
    ){
        Image(painter = painterResource(R.drawable.loading_img), contentDescription = stringResource(R.string.loading))

    }

}
@Composable
fun ErrorScreen(retryAction: () -> Unit,modifier: Modifier = Modifier){
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loadingFailed))
        Button(onClick = retryAction) {
            Text(text = stringResource(R.string.reload))

        }


    }
}
@Composable
fun FrogGrid(frogData: List<Amphibian>, modifier: Modifier){
    LazyColumn( modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp) ){
        items(items = frogData, itemContent = {
            frog -> FrogCard(frog = frog,modifier.padding(start = 10.dp))
        })
    }

}
@Composable
fun FrogCard(frog: Amphibian, modifier: Modifier = Modifier){
    Card( modifier = modifier.background(Color.Green)) {
        Column(modifier.padding(20.dp),) {
            Text(text = frog.name, style = MaterialTheme.typography.headlineMedium )
            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(frog.imgSrc)
                .crossfade(true)
                .build(),
                contentDescription = frog.description,
                contentScale = ContentScale.FillBounds,
                error= painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img)
            )

            Text(text = frog.description, style = MaterialTheme.typography.bodyMedium)
            
        }
        
    }

}