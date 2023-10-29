package com.example.tquest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateIntermediate : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_intermediate)

        dbHelper = DatabaseHelper(this)

        val editQuestion: EditText = findViewById(R.id.editQuestion)
        val editOptionA: EditText = findViewById(R.id.editOptionA)
        val editOptionB: EditText = findViewById(R.id.editOptionB)
        val editOptionC: EditText = findViewById(R.id.editOptionC)
        val editOptionD: EditText = findViewById(R.id.editOptionD)
        val editCorrectAnswer: EditText = findViewById(R.id.editCorrectAnswer)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val question = editQuestion.text.toString()
            val optionA = editOptionA.text.toString()
            val optionB = editOptionB.text.toString()
            val optionC = editOptionC.text.toString()
            val optionD = editOptionD.text.toString()
            val correctAnswer = editCorrectAnswer.text.toString()

            if (question.isNotEmpty() && optionA.isNotEmpty() && optionB.isNotEmpty() && optionC.isNotEmpty() && optionD.isNotEmpty() && correctAnswer.isNotEmpty()) {
                // Insert data into the database
                dbHelper.insertQuestionWithOptions(question, listOf(optionA, optionB, optionC, optionD), correctAnswer)
                // Clear input fields after insertion
                editQuestion.text.clear()
                editOptionA.text.clear()
                editOptionB.text.clear()
                editOptionC.text.clear()
                editOptionD.text.clear()
                editCorrectAnswer.text.clear()
            } else {
                // Handle empty input fields if needed
            }
        }
    }
}
