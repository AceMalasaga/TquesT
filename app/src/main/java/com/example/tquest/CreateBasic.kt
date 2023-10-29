package com.example.tquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class CreateBasic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_basic)

        val createBasicBack : ImageView = findViewById(R.id.imgBackBasicCreate)
        createBasicBack.setOnClickListener{
            onBackPressed()
        }
    }
}