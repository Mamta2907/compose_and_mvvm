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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mamta.fdcompose.ViewModel.LoginViewModel
import com.mamta.fdcompose.ViewModel.ProductViewModel
import com.mamta.fdcompose.ui.theme.FDComposeTheme

class LoginActivity : ComponentActivity() {

   private val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(viewModel)
                }
            }
        }
    }


    @Composable
    private fun LoginScreen(viewModel: LoginViewModel) {

        val username by viewModel.username.collectAsState()
        val password by viewModel.password.collectAsState()
        val loginMessage by viewModel.loginMessage.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.updateUserName(it) },
                label = { Text(text = "Username")}
                )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {viewModel.updatePassword(it)},
                label = { Text(text = "Password")},
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.login() }) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = loginMessage, fontWeight = FontWeight.Bold)
        }
    }

}

