package com.mamta.fdcompose.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mamta.fdcompose.Database.EmployeeDatabase
import com.mamta.fdcompose.Model.Employee
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val employeeDao = EmployeeDatabase.getEmployee(application).employeeDao()

    val allEmployee: StateFlow<List<Employee>> = employeeDao.getAllEmployee().stateIn(
        viewModelScope,
        SharingStarted.Lazily, emptyList()
    )

    val allOlderEmployee: StateFlow<List<Employee>> = employeeDao.getOlderEmployee().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun addEmployee(employee: Employee){
        viewModelScope.launch {
            employeeDao.addEmployee(employee)
        }
    }

    fun deleteEmployee(employee: Employee){
        viewModelScope.launch {
            employeeDao.deleteEmployee(employee)
        }
    }

}