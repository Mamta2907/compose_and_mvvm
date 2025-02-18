package com.mamta.fdcompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.mamta.fdcompose.ViewModel.LoginViewModel
import com.mamta.fdcompose.ViewModel.UserViewModel
import com.mamta.fdcompose.activity.ui.theme.FDComposeTheme
import androidx.compose.ui.res.painterResource

class MyUserActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    userScreen(model = viewModel)
                }
            }
        }
    }
}

@Composable
fun userScreen(model: UserViewModel) {

    val users by model.allUsers.collectAsState()
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Enter Name") },
            //keyboardActions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.)
        )

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = age, onValueChange = { age = it },
            label = { Text(text = "Enter age") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (name.isNotEmpty() && age.isNotEmpty()) {
                model.addUser(name, age.toInt())
                name = ""
                age = ""
            }
        }) {
            Text(text = "Add User")
        }

        LazyColumn {
         items(users){user->
             Row(modifier = Modifier
                 .fillMaxWidth()
                 .padding(8.dp)) {
                 Text(text = "${user.name}, Age: ${user.age}", modifier = Modifier.weight(1f))
                 IconButton(onClick = { model.deleteUser(user) }) {
                     androidx.compose.material3.Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                 }
             }
         }
        }

    }

}
