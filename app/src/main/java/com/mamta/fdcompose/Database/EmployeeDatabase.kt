package com.mamta.fdcompose.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mamta.fdcompose.Dao.EmployeeDao
import com.mamta.fdcompose.Model.Employee

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    companion object {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null
        fun getEmployee(context: Context): EmployeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "emp_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}