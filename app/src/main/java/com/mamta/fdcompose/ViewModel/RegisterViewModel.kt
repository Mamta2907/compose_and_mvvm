package com.mamta.fdcompose.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    /*private val _name = MutableStateFlow<String>("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email

    private val _number = MutableStateFlow<String>("")
    val number: StateFlow<String> = _number

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password*/

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _submittedText = MutableStateFlow("")
    val submittedText: StateFlow<String> = _submittedText

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun submitData() {
        _submittedText.value = "Name: ${_name.value}, Email: ${_email.value}"
    }

    private val _nameList = MutableStateFlow<List<String>>(emptyList())
    val nameList: StateFlow<List<String>> = _nameList

    fun addName(newName: String) {
        if (newName.isNotBlank()) {
            _nameList.value = _nameList.value + newName
        }
    }

}