package com.example.tquest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class CreateHard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_hard)

        val imgBackHardCreate : ImageView = findViewById(R.id.imgBackHardCreate)
        imgBackHardCreate.setOnClickListener{
            onBackPressed()
        }
    }
    fun openWebsite(view: View) {
        val textView = view as TextView
        val url = textView.text.toString()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}