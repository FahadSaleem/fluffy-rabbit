package com.example.fahadsaleem.scproject


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val loginButton = findViewById(R.id.activity_start_button_login) as Button

        loginButton.setOnClickListener {
                startActivity(Intent(this@StartActivity, LoginActivity::class.java))
        }

    }
}