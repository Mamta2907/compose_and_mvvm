package com.mamta.fdcompose.activity

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mamta.fdcompose.Model.Employee
import com.mamta.fdcompose.ViewModel.EmployeeViewModel
import com.mamta.fdcompose.ViewModel.UserViewModel
import com.mamta.fdcompose.activity.ui.theme.FDComposeTheme
import kotlinx.coroutines.flow.StateFlow
import okhttp3.internal.notify

class EmployeeActivity : ComponentActivity() {

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FDComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmployeeData(viewModel)
                }
            }
        }
    }
}

@Composable
private fun EmployeeData(viewModel: EmployeeViewModel) {

    val getEmpList by viewModel.allEmployee.collectAsState()
    val getOlderEmp by viewModel.allOlderEmployee.collectAsState()

    var empId by remember { mutableStateOf("") }
    var empName by remember { mutableStateOf("") }
    var empEmail by remember { mutableStateOf("") }
    var empNumber by remember { mutableStateOf("") }
    var empAge by remember { mutableStateOf("") }
    var empDepart by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = empId,
            onValueChange = { empId = it },
            label = { Text(text = "Enter Employee Id") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = empName,
            onValueChange = { empName = it },
            label = { Text(text = "Enter Employee Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = empEmail,
            onValueChange = { empEmail = it },
            label = { Text(text = "Enter Employee Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = empNumber,
            onValueChange = { empNumber = it },
            label = { Text(text = "Enter Employee Number") })

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = empAge,
            onValueChange = { empAge = it },
            label = { Text(text = "Enter Employee Age") })

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = empDepart,
            onValueChange = { empDepart = it },
            label = { Text(text = "Enter Employee Department") })

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (empId.isNotEmpty() && empName.isNotEmpty() && empEmail.isNotEmpty() && empNumber.isNotEmpty() && empDepart.isNotEmpty() && empAge.isNotEmpty()) {
                val employee = Employee(
                    empAge = empAge.toInt(),
                    employeeId = empId,
                    empName = empName,
                    empEmail = empEmail,
                    empDepartment = empDepart,
                    empNumber = empNumber
                )
                viewModel.addEmployee(employee)
            }

        }) {
            Text(text = "Add Employee")
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TabScreen(getEmpList, getOlderEmp)

    }
}

@Composable
fun TabScreen(empList: List<Employee>, olderEmp: List<Employee>) {

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("All Employee", "Older Employee")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> AllEmployee(empList)
            1 -> OlderEmployee(olderEmp)
        }
    }
}

@Composable
fun AllEmployee(empList: List<Employee>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(empList) {
            Card {
                Row {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Employee Id: ${it.employeeId}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Name: ${it.empName}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Email: ${it.empEmail}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Number: ${it.empNumber}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Department: ${it.empDepartment}")
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Age: ${it.empAge}")

                    }
                }
            }
        }
    }
}

@Composable
fun OlderEmployee(empList: List<Employee>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(empList) {
            Card {
                Row {

                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Employee Id: ${it.employeeId}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Name: ${it.empName}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Email: ${it.empEmail}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Number: ${it.empNumber}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Department: ${it.empDepartment}")
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Age: ${it.empAge}")

                    }
                }
            }
        }
    }
}

/*@Composable
fun EmployeeCard(empList: List<Employee>){
    Card {
        Row {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "Name: ${empList.empName}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Email: ${it.empEmail}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Number: ${it.empNumber}")
            }

            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "Age: ${it.empAge}")

            }

        }
    }
}*/
