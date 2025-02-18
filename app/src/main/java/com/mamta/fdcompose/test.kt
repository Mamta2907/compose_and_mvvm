package com.mamta.fdcompose

import com.mamta.fdcompose.ViewModel.UserViewModel

class test {

   /* @Composable
    fun UserScreen(viewModel: UserViewModel) {
        val users by viewModel.allUsers.collectAsState()
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            TextField(value = name, onValueChange = { name = it }, label = { Text("Enter Name") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Enter Age") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (name.isNotEmpty() && age.isNotEmpty()) {
                    viewModel.addUser(name, age.toInt())
                    name = ""
                    age = ""
                }
            }) {
                Text("Add User")
            }

            LazyColumn {
                items(users) { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = "${user.name}, Age: ${user.age}", modifier = Modifier.weight(1f))
                        IconButton(onClick = { viewModel.deleteUser(user) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }*/

}
