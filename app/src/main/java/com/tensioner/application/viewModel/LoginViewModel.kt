package com.tensioner.application.viewModel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.tensioner.application.AfterLogin
import com.tensioner.application.Interface.LoginResultCallBacks
import com.tensioner.application.RegisterActivity
import com.tensioner.application.models.User
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class LoginViewModel(private val listener:LoginResultCallBacks) : ViewModel() {

    private val user: User

    init {
        this.user = User(email = "", password = "")
    }

    fun onEmailTextChanged(s: CharSequence,start: Int,before : Int,
                              count :Int){
        user.setEmail(s.toString())
    }

    fun onPasswordTextChanged(s: CharSequence,start: Int,before : Int,
                           count :Int){
        user.setPassword(s.toString())
    }
    fun onLoginClicked(v: View) {
         if(user.isDataValid)
             listener.onSuccess("Login Success")
         else
             listener.OnError("Enter Correct Email and Password")
    }

    fun onRegisterClicked(v: View) {
        listener.OnRegister()
    }
}