package com.pascal.simplefinanceapp.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.view.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 200)
    }
}