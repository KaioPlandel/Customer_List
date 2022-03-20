package com.plandel.customerlist.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.plandel.customerlist.R
import com.plandel.customerlist.ui.CustomerMain.CustomerActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        loadingIntroScreen()
    }

    private fun loadingIntroScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            changeActivity()
        }, 3000)
    }
    private fun changeActivity() {
        Intent(this, CustomerActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}