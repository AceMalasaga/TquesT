package com.example.tquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.content.SharedPreferences
import android.widget.TextView

class Board : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesInter: SharedPreferences
    lateinit var sharedPreferencesHard: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        sharedPreferences = applicationContext.getSharedPreferences("scoreBasic", MODE_PRIVATE)
        sharedPreferencesInter = applicationContext.getSharedPreferences("scoreInter", MODE_PRIVATE)
        sharedPreferencesHard = applicationContext.getSharedPreferences("scoreHard", MODE_PRIVATE)

        val imgBackBoard : ImageView = findViewById(R.id.imgBackBoard)
        imgBackBoard.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java )
            startActivity(intent)
        }

        val totalBasic = 25
        val totalInter = 20
        val totalHard = 10

        // getting the score of basic and saving it even if u go to another activity/xml using shared Preference
        val score = sharedPreferences.getInt("Score", 0)
        val basicCorrect : TextView = findViewById(R.id.basicCorrect)
        basicCorrect.text=score.toString()

        // getting the score of Intermediate and saving it even if u go to another activity/xml using shared Preference
        val scoreInter = sharedPreferencesInter.getInt("ScoreInter", 0)
        val interCorrect : TextView = findViewById(R.id.InterCorrect)
        interCorrect.text=scoreInter.toString()

        // getting the score of hard and saving it even if u go to another activity/xml using shared Preference
        val scoreHard =sharedPreferencesHard.getInt("ScoreHard", 0)
        val hardCorrect : TextView = findViewById(R.id.hardCorrect)
        hardCorrect.text=scoreHard.toString()

        val basicPercent = (score.toDouble() / totalBasic.toDouble()) *100
        val interPercent = (scoreInter.toDouble() / totalInter.toDouble()) * 100
        val hardPercent = (scoreHard.toDouble() / totalHard.toDouble()) * 100

        val basicPercentage : TextView = findViewById(R.id.basicPercentage)
        basicPercentage.text = String.format("%.1f%%", basicPercent)

        val InterPercentage : TextView = findViewById(R.id.InterPercentage)
        InterPercentage.text = String.format("%.1f%%", interPercent)

        val hardPercentage : TextView = findViewById(R.id.hardPercentage)
        hardPercentage.text = String.format("%.1f%%", hardPercent)
    }
}