package com.tensioner.application

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.tensioner.application.Interface.NewsService
import kotlinx.android.synthetic.main.activity_splash_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class splash_screen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        JavaHelper.printHashKey(this)
        supportActionBar?.hide()
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        person.startAnimation(topAnim)
        welcome.startAnimation(bottomAnim)
        Handler().postDelayed({
            val uri: Uri? = intent.data

            // checking if the uri is null or not.

            // checking if the uri is null or not.
            if (uri != null) {
                // if the uri is not null then we are getting the
                // path segments and storing it in list.
                val parameters: List<String> = uri.pathSegments

                // after that we are extracting string from that parameters.
                val param = parameters[parameters.size - 1]

                // on below line we are setting
                // that string to our text view
                // which we got as params.
                when (param) {
                    "home" -> {
                        val intent = Intent(this@splash_screen, AfterLogin::class.java)
                        startActivity(intent)
                        finish()
                    }
                    "register" -> {
                        val intent = Intent(this@splash_screen, RegisterActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                    else -> {
                        val intent = Intent(this@splash_screen, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                val intent = Intent(this@splash_screen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)


    }


}