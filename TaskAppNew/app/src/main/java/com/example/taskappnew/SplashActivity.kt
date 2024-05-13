package com.example.taskappnew

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.taskappnew.MainActivity
import com.example.taskappnew.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // Start MainActivity after the delay
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }
}
