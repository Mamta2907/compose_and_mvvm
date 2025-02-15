package com.mamta.fdcompose.ViewModel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {
    private val _username = MutableStateFlow("")
    val username:StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password:StateFlow<String> = _password

    private val _loginMessage = MutableStateFlow("")
    val loginMessage:StateFlow<String> = _loginMessage

    fun updateUserName(newUserName:String){
        _username.value = newUserName
    }

    fun updatePassword(newPassword:String){
        _password.value = newPassword
    }

    fun login(){
        if (_username.value == "admin" && _password.value == "1234"){
            _loginMessage.value = "Login Successful"
        }
        else{
            _loginMessage.value = "Invalid Credentials"
        }
    }

}