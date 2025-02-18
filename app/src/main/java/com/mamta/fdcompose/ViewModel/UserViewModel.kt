package com.mamta.fdcompose.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mamta.fdcompose.Database.UserDatabase
import com.mamta.fdcompose.Model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDatabase.getDatabase(application).userDao()

    val allUsers: StateFlow<List<User>> = userDao
        .getAllUsers()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun addUser(name:String, age:Int){
        viewModelScope.launch {
            userDao.insertUser(User(name = name, age = age))
        }
    }

    fun deleteUser(user:User){
        viewModelScope.launch {
            userDao.deleteUser(user)
        }
    }
}