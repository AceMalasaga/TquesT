package com.example.tquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class BasicPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_page)

        val imgBackBasic : ImageView = findViewById(R.id.imgBackBasic)
        imgBackBasic.setOnClickListener{
            onBackPressed()
        }


        val btnBeginBasic : Button = findViewById(R.id.btnBeginBasic)
        btnBeginBasic.setOnClickListener {
            val intent = Intent(this, BasicLevel::class.java)
            startActivity(intent)
        }
    }
}