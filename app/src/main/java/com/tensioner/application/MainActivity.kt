package com.tensioner.application

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tensioner.application.Interface.LoginResultCallBacks
import com.tensioner.application.databinding.ActivityMainBinding
import com.tensioner.application.viewModel.LoginViewModelFactory
import com.tensioner.application.viewModel.LoginViewModel

class MainActivity : AppCompatActivity(),LoginResultCallBacks{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
       activityMainBinding.viewModel = ViewModelProvider(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)


    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, "login Success", Toast.LENGTH_LONG).show()
        val intent = Intent(this, AfterLogin::class.java)
        startActivity(intent)
    }

    override fun OnError(message: String) {
        Toast.makeText(this, "Enter Correct Email and Password", Toast.LENGTH_LONG).show()
    }

    override fun OnRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }












    /*
    val sharedPreferences: SharedPreferences = this.getSharedPreferences(
        "kotlinsharedpreference",
        Context.MODE_PRIVATE
    )

    //For checking status of checkBox if checkBox true then directly redirect to Afterlogin activity window
    val sharedCheckedBoxValue = sharedPreferences.getString("checkBox", "false")
    if (sharedCheckedBoxValue.equals("true")) {
        val intent = Intent(this, AfterLogin::class.java)
        startActivity(intent)
    } else {
        Toast.makeText(this, "Please Login In", Toast.LENGTH_LONG).show()
    }

    //CheckBox listener for checking user checking state of CheckBox
    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
        if (checkBox.isChecked) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("checkBox", "true")
            editor.apply()
            Toast.makeText(this, "Checked", Toast.LENGTH_LONG).show()
        } else {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("checkBox", "false")
            editor.apply()
            Toast.makeText(this, "Checked", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Unchecked", Toast.LENGTH_LONG).show()
        }
    }

    //login Button to redirect Aferlogin activity  if email and password match
    Login.setOnClickListener {
        val sharedUserName = sharedPreferences.getString("user_name", "defaultusername")
        val sharedEmailId = sharedPreferences.getString("email_id", "defaultname")
        val sharedPassword = sharedPreferences.getString("password", "defaultpassword")
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()
        if (sharedEmailId.equals(email) && sharedPassword.equals(password)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
            val intent = Intent(this, AfterLogin::class.java)
            startActivity(intent)
             }
        else
            Toast.makeText(this, "User Not Found", Toast.LENGTH_LONG).show()


    }

    //sign up button to redirect signup activity window
    signup.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}

     */
}