package com.mamta.fdcompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mamta.fdcompose.ViewModel.RegisterViewModel
import com.mamta.fdcompose.ui.theme.FDComposeTheme

class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                ) {
                    //Greeting2("Android")
                    //Register(viewModel)
                    NameListScreen(viewModel)

                }
            }
        }
    }
}

@Composable
fun NameListScreen(viewModel: RegisterViewModel) {

    val nameList by viewModel.nameList.collectAsState()
    var newName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        TextField(
            value = newName,
            onValueChange = { newName = it },
            label = { Text(text = "Enter the name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addName(newName)
            newName = ""
        }) {
            Text(text = "Add Name")
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(nameList) { name ->
                Text(
                    text = name,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun Register(viewModel: RegisterViewModel) {

    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val submittedMessage by viewModel.submittedText.collectAsState()


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = name,
            onValueChange = { viewModel.updateName(it) },
            label = { Text(text = "Enter name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text(text = "Enter Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.submitData() })
        {
            Text(text = "Submit")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Submitted Data: $submittedMessage",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

