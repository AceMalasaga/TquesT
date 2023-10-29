package com.example.tquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class InterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inter_page)

        val imgBackInter : ImageView = findViewById(R.id.imgBackInter)
        imgBackInter.setOnClickListener{
            onBackPressed()
        }


        val btnBeginInter : Button = findViewById(R.id.btnBeginInter)
        btnBeginInter.setOnClickListener{
            val intent = Intent(this,InterLevel::class.java)
            startActivity(intent)
        }
    }
}