/*
package com.mamta.fdcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mamta.fdcompose.Model.FruitsModel
import com.mamta.fdcompose.ui.theme.FDComposeTheme

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Calling the custom list function here
                    CustomList(fruitList = getFruitsList())
                }
            }
        }
    }

    // Function to generate the list of fruits
  */
/*  private fun getFruitsList(): List<FruitsModel> {
        return listOf(
            FruitsModel("Apple", R.drawable.apple),  // Replace with actual drawable
            FruitsModel("Banana", R.drawable.banana),
            FruitsModel("Cherry", R.drawable.cherry),
            FruitsModel("Mango", R.drawable.mango),
            FruitsModel("Orange", R.drawable.orange)
        )
    }*//*

}

@Composable
fun CustomList(fruitList: List<FruitsModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(fruitList) { fruit ->
            ListRow(model = fruit)
        }
    }
}

@Composable
fun ListRow(model: FruitsModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = Color.Magenta)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = model.image),
            contentDescription = model.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(5.dp)
        )
        Text(
            text = model.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}
*/
