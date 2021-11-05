package com.tensioner.application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        register.setOnClickListener {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            if(((inputUserNameRegister.text.toString()) != "") && ((inputEmailRegister.text.toString()) != "") && ((inputPasswordRegister.text.toString()) != "")) {
                editor.putString("user_name", inputUserNameRegister.text.toString())
                editor.putString("email_id", inputEmailRegister.text.toString())
                editor.putString("password", inputPasswordRegister.text.toString())
                editor.apply()
                editor.commit()
                Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Field Can't be empty", Toast.LENGTH_LONG).show()
            }
        }
        loginpage.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}