package com.mamta.fdcompose.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var empAge:Int,
    var employeeId:String,
    var empName:String,
    var empEmail:String,
    var empNumber:String,
    var empDepartment:String,
)
