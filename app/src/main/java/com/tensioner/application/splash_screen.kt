package com.tensioner.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class splash_screen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        person.startAnimation(topAnim)
        welcome.startAnimation(bottomAnim)
        Handler().postDelayed({
            val intent = Intent(this@splash_screen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5500)


    }
}