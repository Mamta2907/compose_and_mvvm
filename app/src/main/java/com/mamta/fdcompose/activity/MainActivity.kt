package com.mamta.fdcompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mamta.fdcompose.ViewModel.ProductViewModel
import com.mamta.fdcompose.Model.Product
import com.mamta.fdcompose.ui.theme.FDComposeTheme
import coil.compose.rememberAsyncImagePainter


class MainActivity : ComponentActivity() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductList(viewModel)
                }
            }
        }
    }
}

@Composable
private fun ProductList(viewModel: ProductViewModel) {

    val products by viewModel.productResponse.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProductList()
    }
    val listModifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)
        .padding(10.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Product List", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(modifier = listModifier) {
            items(products) { products ->
                ProductItem(product = products)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Product Thumbnail Image
            Image(
                painter = rememberAsyncImagePainter(product.thumbnail),
                contentDescription = "Product Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title & Brand
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                fontSize = 18.sp
            )
            Text(
                text = "Brand: ${product.brand}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Price & Discount
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "₹${product.price}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    fontSize = 18.sp,
                    color = Color.Green
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${product.discountPercentage}% OFF",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rating & Stock Status
            Row(verticalAlignment = Alignment.CenterVertically) {
                RatingStars(rating = product.rating)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = if (product.stock > 0) "In Stock (${product.stock})" else "Out of Stock",
                    color = if (product.stock > 0) Color.Green else Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Category & Tags
            Text(text = "Category: ${product.category}", fontSize = 14.sp, color = Color.Gray)
            LazyRow {
                items(product.tags) { tag ->
                    Text(
                        text = "#$tag",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Expandable Description
            Text(
                text = "Description: ${
                    if (expanded) product.description else product.description.take(
                        50
                    )
                }...",
                fontSize = 14.sp,
                modifier = Modifier.clickable { expanded = !expanded },
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Buy Button
            Button(
                onClick = { /* Handle Buy Action */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Buy Now")
            }
        }
    }
}

// RatingStars Composable to Show Star Ratings ⭐⭐⭐⭐⭐
@Composable
fun RatingStars(rating: Double) {
    Row {
        repeat(rating.toInt()) {
            Text(text = "⭐", fontSize = 16.sp)
        }
    }
}

