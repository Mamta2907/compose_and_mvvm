package com.mamta.fdcompose.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SecondViewModel() : ViewModel() {

    private val _isDark = MutableStateFlow<Boolean>(false)
    var isDark: StateFlow<Boolean> = _isDark


    fun updateUi(newValue:Boolean){

    }


    /*    //Counter App
        private val _count = MutableStateFlow<Int>(0)
        var count:StateFlow<Int> = _count

        fun increment(){
            _count.value += 1
        }

        fun decrement(){
            _count.value -= 1
        }*/

}