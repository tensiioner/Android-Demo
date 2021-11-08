package com.tensioner.application.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tensioner.application.Interface.LoginResultCallBacks
import java.util.*

class LoginViewModelFactory(private val listener: LoginResultCallBacks): ViewModelProvider.NewInstanceFactory() {
        override fun  <T:ViewModel?> create(modelClass: Class<T>): T{
            return LoginViewModel(listener) as T
        }


}