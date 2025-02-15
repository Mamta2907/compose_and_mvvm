package com.mamta.fdcompose.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mamta.fdcompose.ViewModel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(viewModel: ProductViewModel ) {
    val products by viewModel.productResponse.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProductList() // Fetch products when screen starts
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Product List") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (errorMessage.isNotEmpty()) {
                Text(text = "Error: $errorMessage", color = MaterialTheme.colorScheme.error)
            } else if (products.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            } else {
                LazyColumn {
                    items(products) { product ->
                        //ProductItem(product)
                    }
                }
            }
        }
    }
}

//@Composable
/*fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        //elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(product.thumbnail),
                contentDescription = product.title,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Text(text = product.title, style = MaterialTheme.typography.h6)
            Text(text = "Price: $${product.price}", style = MaterialTheme.typography.body1)
            Text(text = "Stock: ${product.stock}", style = MaterialTheme.typography.body2)
        }
    }
}*/
