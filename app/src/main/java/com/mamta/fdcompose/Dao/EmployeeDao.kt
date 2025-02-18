package com.mamta.fdcompose.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mamta.fdcompose.Model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmployee(employee: Employee)

    @Query("SELECT * FROM employee_table ORDER BY id ASC")
     fun getAllEmployee(): Flow<List<Employee>>

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employee_table WHERE empAge > 25")
     fun getOlderEmployee(): Flow<List<Employee>>


}