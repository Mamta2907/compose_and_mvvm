package com.mamta.fdcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.mamta.fdcompose.Model.FruitsModel
import com.mamta.fdcompose.ViewModel.ProductViewModel
import com.mamta.fdcompose.ViewModel.SecondViewModel
import com.mamta.fdcompose.ui.theme.FDComposeTheme
import kotlinx.coroutines.flow.MutableStateFlow

class SecondActivity : ComponentActivity() {
    //private val viewModel: SecondViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //CounterScreen(viewModel)
                    customList(getFruitsList())
                 }
             }
         }
     }


    @Composable
    private fun ListRow(model: FruitsModel) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            Image(
                painter = painterResource(id = model.image),
                contentDescription = "",
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

    private fun getFruitsList():List<FruitsModel>{
        return listOf(
            FruitsModel("Apple", R.drawable.baseline_cake_24),  // Replace with actual drawable
            FruitsModel("Banana", R.drawable.baseline_food_bank_24),
            FruitsModel("Cherry", R.drawable.baseline_cake_24),
            FruitsModel("Mango", R.drawable.baseline_food_bank_24),
            FruitsModel("Orange", R.drawable.baseline_cake_24)
        )
    }

    @Composable
    private fun customList(fruitList:List<FruitsModel>){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(fruitList){fruit ->
                ListRow(model = fruit)
            }
        }
    }


    /*@Composable
    private fun CounterScreen(viewModel: SecondViewModel) {

        val counterValue by viewModel.count.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Counter: $counterValue",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = { viewModel.decrement() }) {
                    Text(text = "Decrement")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = { viewModel.increment() }) {
                    Text(text = "Increment")
                }
            }
        }
    }*/

}




