package com.icalshapa.kertasguntingbatu.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.bumptech.glide.Glide
import com.icalshapa.kertasguntingbatu.databinding.ActivitySplashscreenBinding
import com.icalshapa.kertasguntingbatu.ui.intro.IntroActivity

class SplashscreenActivity : AppCompatActivity() {
    private lateinit var binding :ActivitySplashscreenBinding
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        loadImageSplash()
        setEventSplash()
    }

    private fun loadImageSplash() {
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .fitCenter()
            .into(binding.ivSplashTitle)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(timer != null){
            timer?.cancel()
            timer = null
        }
    }

    private fun setEventSplash() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val intent = Intent(this@SplashscreenActivity, IntroActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

        }
        timer?.start()
    }
}