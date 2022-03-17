package com.plandel.customerlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.plandel.customerlist.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        loadingIntroScreen()

    }

    private fun loadingIntroScreen() {

        Handler().postDelayed({
            Intent(this,CustomerActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, 3000L)
    }
}