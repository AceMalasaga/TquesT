package com.example.tquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HardPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_page)

        val imgBackHard : ImageView = findViewById(R.id.imgBackHardPage)
        imgBackHard.setOnClickListener{
            onBackPressed()
        }

        val btnBeginHard : Button = findViewById(R.id.btnBeginHard)

        btnBeginHard.setOnClickListener {
            val intent = Intent(this, HardLevel::class.java)
            startActivity(intent)
        }
    }
}