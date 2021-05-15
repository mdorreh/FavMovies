package com.example.favmovies.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import com.example.favmovies.R
import com.example.favmovies.databinding.ActivitySplashBinding
import com.example.favmovies.presentation.view.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val splashAnimation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        splashBinding.textView.animation = splashAnimation
        splashAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                Handler(Looper.getMainLooper()).postDelayed(
                    { startActivity(Intent(this@SplashActivity, MainActivity::class.java)) },
                    1000
                )
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        }
        )
    }
}