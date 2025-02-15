package com.mamta.fdcompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mamta.fdcompose.Model.CreateAddressBody
import com.mamta.fdcompose.Model.ProductModel
import com.mamta.fdcompose.ViewModel.AddressViewModel
import com.mamta.fdcompose.ViewModel.LoginViewModel
import com.mamta.fdcompose.ViewModel.ProductViewModel
import com.mamta.fdcompose.ui.theme.FDComposeTheme

class ScrapProductsActivity : ComponentActivity() {
    private val addressViewModel: AddressViewModel by viewModels()
    private val model: ProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting2("Android")
                    createAddress(model = addressViewModel)
                    //myProductList(model = model)
                }
            }
        }
    }
}

@Composable
private fun createAddress(model: AddressViewModel) {
    var address by remember { mutableStateOf("") }
    var landmark by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }

    val response by model.addressResponse.collectAsState()
    val error by model.errorMessage.collectAsState()
    val isLoading by model.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
        TextField(
            value = landmark,
            onValueChange = { landmark = it },
            label = { Text(text = "Landmark") })

        TextField(
            value = pincode,
            onValueChange = { pincode = it },
            label = { Text(text = "Pincode") })

        TextField(value = city, onValueChange = { city = it }, label = { Text(text = "City") })

        Button(
            onClick = {
                val requestBody = CreateAddressBody(
                    address = address,
                    area = "Test Area",
                    c_id = 13,
                    first_name = "Rnd",
                    city = city,
                    landmark = landmark,
                    last_name = "Technosoft",
                    lat = "22.25",
                    long = "33.11",
                    pincode = pincode,
                    state = "Gujarat"
                )
                model.createAddress(requestBody)
            },
            enabled = !isLoading
        ) {
            Text(text = ("Create Address"))
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        response?.let {
            Text(text = "Created Address Id: ${it.address.id}", color = Color.Green)
        }

        error?.let {
            Text(text = it, color = Color.Red)
        }
    }
}

@Composable
private fun ProductItem(productModel: ProductModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Card {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                model = productModel.product_image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = productModel.product_name, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = productModel.product_price, textAlign = TextAlign.Center)

        }
    }
}


@Composable
private fun myProductList(model: ProductViewModel) {

    val productList by model.scrapProductList.collectAsState()

    LaunchedEffect(Unit) {
        model.getScrapProductList()
    }

    /*LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
         items(productList){product->
             ProductItem(productModel = product)
         }
    }*/

    //Grid layout in 2 Rows Horizontal

    /*LazyHorizontalStaggeredGrid(rows = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        //horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(productList){
                ProductItem(productModel = it)
            }
    }*/

    //Grid Layout in vertical
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(productList) {
            ProductItem(productModel = it)
        }
    }
}