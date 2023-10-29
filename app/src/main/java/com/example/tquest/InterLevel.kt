package com.example.tquest

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

data class tofItem(
    val question : String,
    val options : List<String>,
    val correctAnswerIndex : Int
)

class InterLevel : AppCompatActivity() {
    //declare UI element with lateinit
    private lateinit var countdownTimerInter : TextView
    private lateinit var questionInter : TextView
    private lateinit var optionsRadioGroupInter : RadioGroup
    private lateinit var optTrue : RadioButton
    private lateinit var optFalse : RadioButton
    private lateinit var scoreInter : TextView
    private lateinit var btnNextInter: Button
    private var nextButtonClickedCountInter = 0

    private var score : Int = 0
    private var questionLimit = 20
    private val usedQuestion : MutableList<tofItem> = mutableListOf()
    private lateinit var currentQuestion:tofItem

    private val interQuestion = arrayOf(
        //40 questions
        tofItem("Enumerations are a special data type in Java that allows for a variable to be set to a predefined variable.", listOf("True", "False"),1),
        tofItem("The modulus operator (%) in Java can be used only with variables of integer type.", listOf("True", "False"),2),
        tofItem("Declarations must appear at the start of the body of a Java method.", listOf("True", "False"),2),
        tofItem("The switch selection structure must end with the default case.", listOf("True", "False"),2),
        tofItem("A break statement must always be present in the default case of a \"switch\" selection structure.", listOf("True", "False"),2),
        tofItem("An individual array element from an array of type int, when passed to a method is passed by value.", listOf("True", "False"),1),
        tofItem("Objects of a subclass can be assigned to a super class reference.", listOf("True", "False"),1),
        tofItem("James Gosling is father of Java.", listOf("True", "False"),1),
        tofItem("Java technology is both a programming language and a platform.", listOf("True", "False"),1),
        tofItem("The modifiers public and static cannot written in either order \"public static\" or \"static public\".", listOf("True", "False"),1),
        tofItem("Java is short for \"JavaScript\".", listOf("True","False"),2),
        tofItem("Can 8 byte long data type be automatically type cast to 4 byte float data type.", listOf("True","False"),1),
        tofItem("In an instance method or a constructor, \"this\" is a reference to the current object.", listOf("True","False"),1),
        tofItem("Constructor overloading is not possible in Java.", listOf("True","False"),2),
        tofItem("Variable name can begin with a letter, \"\$\", or \"_\".", listOf("True","False"),1),
        tofItem("Variables declared inside a for loop are limited in scope to the loop.", listOf("True","False"),2),
        tofItem("An array in the Java programming language has the ability to store many different types of values.", listOf("True","False"),2),
        tofItem("An individual array element from an array of type int, when passed to a method is passed by value.", listOf("True","False"),1),
        tofItem("The == operator can be used to compare two String objects. The result is always true if the two strings are identical", listOf("True","False"),2),
        tofItem("The expression (y >= z && a == b) is evaluated by first evaluating the expression y >= z, and then evaluating a == b.", listOf("True","False"),2),

        tofItem("Variable name identifiers must begin with a lowercase letter.", listOf("True","False"),2),
        tofItem("my Value is not a valid Java identifier.", listOf("True","False"),1),
        tofItem("Real is a java primitive type.", listOf("True","False"),2),
        tofItem("An extra blank line cannot cause a syntax error to be reported by the Java compiler.", listOf("True","False"),1),
        tofItem("In Java, arithmetic expressions are evaluated from left to right when the precedence of the operators are the same.", listOf("True","False"),1),
        tofItem("nextInt is a Scanner method for inputting an integer value.", listOf("True","False"),1),
        tofItem("In your Java program, you can use reserved words as variable names.", listOf("True","False"),2),
        tofItem("The syntax of a particular computer language is a set of rules defining the grammar of that language.", listOf("True","False"),2),
        tofItem("Java is a case-sensitive language", listOf("True","False"),1),
        tofItem("Java is considered a low-level programming language.", listOf("True","False"),2),

        tofItem("Classes in the same package are implicitly imported into main.", listOf("True","False"),2),
        tofItem("Instance variables can be declared anywhere inside a class.", listOf("True","False"),2),
        tofItem("To call a method of an object, follow the object name with a comma, the method name and a set of parentheses containing the method's arguments.", listOf("True","False"),2),
        tofItem("Keyword null indicates that a method will perform a task but will not return any information.", listOf("True","False"),2),
        tofItem("Constructors can specify parameters but not return types.", listOf("True","False"),1),
        tofItem("Reference-type instance variables are initialized by default to the value void.", listOf("True","False"),2),
        tofItem("Classes often provide public methods to allow the class's clients to set or get private instance variables; the names of these methods must begin with set or get.", listOf("True","False"),2),
        tofItem("Variables of type boolean are initialized to true.", listOf("True","False"),2),
        tofItem("When a program executes, the first statement to execute is always the first statement in the main method.", listOf("True","False"),1),
        tofItem("Void methods must have at least one parameter.", listOf("True","False"),2),

        )

    private val radioButtonsMap = mapOf(
        R.id.optTrue to 1,
        R.id.optFalse to 2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inter_level)

        val imgbackInterMain : ImageView = findViewById(R.id.imgbackInterMain)
        imgbackInterMain.setOnClickListener(){
            onBackPressed()
        }

        //finding and putting a variable from xml to kotlin
        countdownTimerInter = findViewById(R.id.countdownTimerInter)
        questionInter = findViewById(R.id.questionInter)
        optionsRadioGroupInter = findViewById(R.id.optionsRadioGroupInter)
        optTrue = findViewById(R.id.optTrue)
        optFalse = findViewById(R.id.optFalse)
        scoreInter = findViewById(R.id.scoreInter)


        val btnBackInter : ImageView = findViewById(R.id.imgbackInterMain)
        //btnBackInter set to click and event to back to the page of inter
        btnBackInter.setOnClickListener{
           showQuitConfirmationDialog()
        }

        btnNextInter = findViewById(R.id.btnNextInter)
        btnNextInter.setOnClickListener{
            if (nextButtonClickedCountInter < 8) {
                loadNextQuestion()
                nextButtonClickedCountInter++
            } else {
                showToast("You have reached the maximum limit of the next.")
                btnNextInter.isEnabled = false // Disable the button after reaching the limit
            }
        }

        val btnSubmitInter : Button = findViewById(R.id.btnSubmitInter)
        btnSubmitInter.setOnClickListener {
            handleAnswerSubmission()
        }
        startTimer()
        loadNextQuestion()

    }

    private fun startTimer(){
        // set the countdown to 40 mins
        object : CountDownTimer(2400000, 1000) { // 900000 milliseconds = 15 minutes
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                countdownTimerInter.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                countdownTimerInter.text = "Time's up!"
                handleAnswerSubmission()
                loadNextQuestion()
            }
        }.start()
    }

    private fun loadNextQuestion(){
        if (questionLimit > 0){
            //the array interQuestion tofItem are the object
            var newQuestion : tofItem
            do {
                newQuestion = interQuestion.random()
            } while (usedQuestion.contains(newQuestion))

            usedQuestion.add(newQuestion)
            currentQuestion = newQuestion
            setQuestion(currentQuestion)
            optionsRadioGroupInter.clearCheck()

            //questionLimit--
        }
        else{
            val sharedPreferences = getSharedPreferences("scoreInter", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("ScoreInter", score)
            editor.apply()

            showToast("End of the Quiz!")
            val intent = Intent(this,Board::class.java)
            intent.putExtra("ScoreInter", score)
            startActivity(intent)
        }
    }

    private fun handleAnswerSubmission(){
        if (countdownTimerInter.text.toString()== "Time's up!"){
            val sharedPreferences = getSharedPreferences("scoreInter", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("ScoreInter", score)
            editor.apply()

            // Delay for 3 seconds before redirecting to Board activity
            Handler().postDelayed({
                val intent = Intent(this, Board::class.java)
                startActivity(intent)
                finish() // Finish the current activity to prevent going back to it
            }, 2000) // 3000 milliseconds = 3 seconds delay

            return
        }

        val selectedRadioButtonId = optionsRadioGroupInter.checkedRadioButtonId

        if (selectedRadioButtonId == -1) {
            showToast("Please select an answer!")
            return
        }

        val selectedAnswerIndex = radioButtonsMap[optionsRadioGroupInter.checkedRadioButtonId] ?: -1
        val correctAnswerIndex = currentQuestion.correctAnswerIndex

        val isAnswerCorrect = selectedAnswerIndex == correctAnswerIndex

        showToast(if (isAnswerCorrect)"Correct Answer!" else "Incorrect Answer!")

        highlightAnswer(isAnswerCorrect)

        if (isAnswerCorrect){
            score++
        }

        if (questionLimit > 0){
            questionLimit--
        }
        scoreInter.text= "Score: $score"

        Handler().postDelayed({
            resetUI()
            loadNextQuestion()
        },1000)

    }

    private fun setQuestion(interQuestion : tofItem){
        val questionInter : TextView = findViewById(R.id.questionInter)
        questionInter.text= interQuestion.question
        optTrue.text = interQuestion.options[0]
        optFalse.text = interQuestion.options[1]
    }

    private fun showToast(message: String) {
        Toast.makeText(this@InterLevel, message, Toast.LENGTH_SHORT).show()
    }

    private fun highlightAnswer(isCorrect: Boolean) {
        val selectedRadioButton: RadioButton = findViewById(optionsRadioGroupInter.checkedRadioButtonId)
        selectedRadioButton.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)
    }

    private fun resetUI() {
        optTrue.setBackgroundResource(R.drawable.radio_button_background)
        optFalse.setBackgroundResource(R.drawable.radio_button_background)
    }

    private fun showQuitConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Are you sure you want to quit the quiz?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                onBackPressed()
                usedQuestion.clear()
                score = 0
                // Restart the quiz
                loadNextQuestion()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}