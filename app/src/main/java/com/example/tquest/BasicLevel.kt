package com.example.tquest


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.app.AlertDialog
import android.content.ContentValues
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.TintableBackgroundView

data class McqItem(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

class BasicLevel : AppCompatActivity() {
    // this code is for late initialize
    private lateinit var countdownTimer: TextView
    private lateinit var opt1: RadioButton
    private lateinit var opt2: RadioButton
    private lateinit var opt3: RadioButton
    private lateinit var opt4: RadioButton
    private lateinit var optionsRadioButton: RadioGroup
    private lateinit var basicScore: TextView
    private lateinit var btnNext : Button
    private var nextButtonClickedCount = 0

    private val usedQuestions: MutableList<McqItem> = mutableListOf() //to ensure the next question is not repeated
    private var score: Int = 0 //to monitor the score
    private lateinit var currentQuestion: McqItem //initialize the currentQuestion later
    private var questionLimit = 25 // set the questions limit

    private val mcqItem = arrayOf(
        //40 questions
        McqItem("Who invented Java Programming?", listOf("a) Guido van Rossum", "b) James Gosling", "c) Dennis Ritchie", "d) Bjarne Stroustrup"), 1),
        McqItem("Which statement is true about Java?", listOf("a) Java is a sequence-dependent programming language", "b) Java is a code dependent programming language", "c) Java is a platform-dependent programming language", "d) Java is a platform-independent programming language"), 3),
        McqItem("Which component is used to compile, debug and execute the java programs?", listOf("a) JRE", "b) JIT", "c) JDK", "d) JVM"), 2),
        McqItem("Which one of the following is not a Java feature?", listOf("a) Object-oriented", "b) Use of pointers", "c) Portable","d) Dynamic and Extensible"),2),
        McqItem("Which of these cannot be used for a variable name in Java?", listOf("a) identifier & keyword", "b) identifier", "c) keyword", "d) none of the mentioned"), 3),
        McqItem("What is the extension of java code files?", listOf("a) .js", "b) .txt", "c) .class", "d) .java"), 4),
        McqItem("Which of the following is not an Object-oriented programming concept in Java?", listOf("a) Polymorphism", "b) Inheritance", "c) Compilation", "d) Encapsulation"), 3),
        McqItem("What is not the use of “this” keyword in Java?", listOf("a) Referring to the instance variable when a local variable has the same name", "b) Passing itself to the method of the same class", "c) Passing itself to another method","d) Calling another constructor in constructor chaining"),2),
        McqItem("What is the extension of compiled java classes?", listOf("a) .txt","b) .js","c) .class","d) .java"),3),
        McqItem("Which of these are selection statements in Java?", listOf("a) break","b) continue", "c) for()", "d) if()"),4),
        McqItem("What is a correct syntax to output \"Hello World\" in Java?", listOf("a) print(\"Hello World\")","b) Console.Write.Line(\"Hello World\")","c) System.out.println(\"Hello World\")","d) echo(\"Hello World\")"),3),
        McqItem("How do you insert COMMENTS in Java code?", listOf("a) /*","b) #","c) //","d) ++"),3),
        McqItem("Which of these keywords can be used to prevent inheritance of a class?", listOf("a) super","b) constant","c) class","d) final"),4),
        McqItem("An expression involving byte, int, and literal numbers is promoted to which of these?", listOf("a) int","b) long","c) byte","d) float"),1),
        McqItem("Which data type value is returned by all transcendental math functions?", listOf("a) int","b) float","c) double","d) long"),3),
        McqItem("Modulus operator, %, can be applied to which of these?", listOf("a) Integers","b) Floating – point numbers","c) Both Integers and floating – point numbers","d) None of the mentioned"),3),
        McqItem("Decrement operator, −−, decreases the value of variable by what number?", listOf("a) 1","b) 2","c) 3","d) 4"),1),
        McqItem("Which of the following is a type of polymorphism in Java?", listOf("a) Compile time polymorphism","b) Execution time polymorphism","c) Multiple polymorphism","d) Multilevel polymorphism"),1),
        McqItem("When does method overloading is determined?", listOf("a) At run time","b) At compile time","c) At coding time","d) At execution time"),2),
        McqItem("Which concept of Java is a way of converting real world objects in terms of class?", listOf("a) Polymorphism","b) Encapsulation","c) Abstraction","d) Inheritance"),3),
        McqItem("Which of these keywords is used to make a class?", listOf("a) class","b) struct","c) int","d) none of the mentioned"),1),
        McqItem("Which of the following is a valid declaration of an object of class Box?", listOf("a) Box obj = new Box();","b) Box obj = new Box;","c) obj = new Box();","d) new Box obj;"),1),
        McqItem("Which of these operators is used to allocate memory for an object?", listOf("a) malloc","b) alloc","c) new","d) give"),3),
        McqItem("Which of these statement is incorrect?", listOf("a) Every class must contain a main() method","b) Applets do not require a main() method at all","c) There can be only one main() method in a program","d) main() method must be made public"),1),
        McqItem("Which of the following statements is correct?", listOf("a) Public method is accessible to all other classes in the hierarchy","b) Public method is accessible only to subclasses of its parent class","c) Public method can only be called by object of its class","d) Public method can be accessed by calling object of the public class"),1),
        McqItem("What is the return type of a method that does not return any value?", listOf("a) int","b) float","c) void","d) double"),3),
        McqItem("Which of the following is a method having same name as that of it’s class?", listOf("a) finalize","b) delete","c) class","d) constructor"),4),
        McqItem("Which method can be defined only once in a program?", listOf("a) main method","b) finalize method","c) static method","d) private method"),1),
        McqItem("Which of this statement is incorrect?", listOf("a) All object of a class are allotted memory for the all the variables defined in the class","b) If a function is defined public it can be accessed by object of other class by inheritation","c) main() method must be made public","d) All object of a class are allotted memory for the methods defined in the class"),4),
        McqItem("Which of these class is superclass of every class in Java?", listOf("a) String class","b) Object class","c) Abstract class","d) ArrayList class"),2),
        McqItem("Which of these method of Object class can clone an object?", listOf("a) Objectcopy()","b) copy()","c) Object clone()","d) clone()"),3),
        McqItem("Which of these method of Object class is used to obtain class of an object at run time?", listOf("a) get()","b) void getclass()","c) Class getclass()","d) None of the mentioned"),3),
        McqItem("Which of these keywords cannot be used for a class which has been declared final?", listOf("a) abstract","b) extends","c) abstract and extends","d) none of the mentioned"),1),
        McqItem("Which of these class relies upon its subclasses for complete implementation of its methods?", listOf("a) Object class","b) abstract class","c) ArrayList class","d) None of the mentioned"),2),
        McqItem("Which of these method of class String is used to compare two String objects for their equality?", listOf("a) equals()","b) Equals()","c) isequal()","d) Isequal()"),1),
        McqItem("Which of these methods of class String is used to check whether a given object starts with a particular string literal?", listOf("a) startsWith()","b) endsWith()","c) Starts()","d) ends()"),1),
        McqItem("Which of these data type value is returned by equals() method of String class?", listOf("a) char","b) int","c) boolean","d) all of the mentioned"),3),
        McqItem("Which of these is a super class of wrappers Long, Character & Integer?", listOf("a) Long","b) Digits","c) Float","d) Number"),4),
        McqItem("Which of these is a wrapper for simple data type char?", listOf("a) Float","b) Character","c) String","d) Integer"),2),
        McqItem("Which of these is method for testing whether the specified element is a file or a directory?", listOf("a) IsFile()","b) isFile()","c) Isfile()","d) isfile()"),2),
        McqItem("What is the output of relational operators?", listOf("a) Integer","b) Boolean","c) Characters","d) Double"),2),
        McqItem("Which of these is returned by “greater than”, “less than” and “equal to” operators?",listOf("a) Integers","b) Floating – point numbers","c) Boolean","d) None of the mentioned"),3),
        McqItem("Which of these operators can skip evaluating right hand operand?", listOf("a) !","b) |","c) &","d) &&"),4)
        // all questions and answer
    )

    private val radioButtonsMap = mapOf(
        //the numbers 1 to 4 are corresponding to the radio button
        R.id.opt1 to 1,
        R.id.opt2 to 2,
        R.id.opt3 to 3,
        R.id.opt4 to 4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_level)

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase

        for (mcq in mcqItem) {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_QUESTION, mcq.question)
                put(DatabaseHelper.COLUMN_OPTIONS, mcq.options.joinToString(separator = "|"))
                put(DatabaseHelper.COLUMN_CORRECT_ANSWER_INDEX, mcq.correctAnswerIndex)
            }

            db.insert(DatabaseHelper.TABLE_NAME, null, values)
        }

        db.close()

        // Initialize UI elements
        countdownTimer = findViewById(R.id.countdownTimer)
        opt1 = findViewById(R.id.opt1)
        opt2 = findViewById(R.id.opt2)
        opt3 = findViewById(R.id.opt3)
        opt4 = findViewById(R.id.opt4)
        optionsRadioButton = findViewById(R.id.optionsRadioGroup)
        basicScore = findViewById(R.id.basicScore)

        val btnback: ImageView = findViewById(R.id.btnback)
        btnback.setOnClickListener {
            showQuitConfirmationDialog()
        }

        btnNext = findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            if (nextButtonClickedCount < 10) {
                loadNextQuestion()
                nextButtonClickedCount++
            } else {
                showToast("You have reached the maximum limit of the next.")
                btnNext.isEnabled = false // Disable the button after reaching the limit
            }
        }

        val btnSubmit: Button = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            handleAnswerSubmission()
        }

        //initializing to start the timer and load a question
        startTimer()
        loadNextQuestion()
    }

    private fun startTimer() {
        object : CountDownTimer(2400000 , 1000) { // 2,400,000 milliseconds = 40 minutes interval 1 sec
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                countdownTimer.text = String.format("%02d:%02d", minutes, seconds) //to set the format in the ui
            }

            override fun onFinish() {
                countdownTimer.text = "Time's up!"
                handleAnswerSubmission()//check the answer if it's correct
                loadNextQuestion()
            }
        }.start()//time start after it click the start
    }

    private fun loadNextQuestion() {
        if (questionLimit > 0){// if the question limit is greater execute the if
            var newQuestion: McqItem
            //the do-while loop ensures that the selected question has not been used before
            do {
                newQuestion = mcqItem.random()
            } while (usedQuestions.contains(newQuestion))

            usedQuestions.add(newQuestion)// the newly selected question is added to the usedQuestion
            currentQuestion = newQuestion //current question is updated
            setQuestion(currentQuestion)
            optionsRadioButton.clearCheck()

            //questionLimit--
        }
        else {
            val sharedPreferences = getSharedPreferences("scoreBasic", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("Score", score)
            editor.apply()

            showToast("End of the Quiz!")
            val intent = Intent(this, Board::class.java)
            intent.putExtra("Score", score)
            startActivity(intent)
        }
    }

    private fun handleAnswerSubmission() {
        // Check if the timer has finished
        if (countdownTimer.text.toString() == "Time's up!") {
            // Save the score in SharedPreferences
            val sharedPreferences = getSharedPreferences("scoreBasic", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("Score", score)
            editor.apply()

            // Delay for 3 seconds before redirecting to Board activity
            Handler().postDelayed({
                val intent = Intent(this, Board::class.java)
                startActivity(intent)
                finish() // Finish the current activity to prevent going back to it
            }, 2000) // 3000 milliseconds = 3 seconds delay

            return
        }

        val selectedRadioButtonId = optionsRadioButton.checkedRadioButtonId

        if (selectedRadioButtonId == -1) {
            showToast("Please select an answer!")//if the user don't select question a toast will displayed
            return
        }

        val selectedAnswerIndex = radioButtonsMap[optionsRadioButton.checkedRadioButtonId] ?: -1 //the selected answer stored in answer index
        val correctAnswerIndex = currentQuestion.correctAnswerIndex // the current question and the answer stored in the correct answer index

        val isAnswerCorrect = selectedAnswerIndex == correctAnswerIndex //checking

        showToast(if (isAnswerCorrect) "Correct Answer!" else "Incorrect Answer")

        // Highlight the correct/wrong answer
        highlightAnswer(isAnswerCorrect)

        // Increment score for correct answers
        if (isAnswerCorrect) {
            score++
        }

        if(questionLimit > 0){
            questionLimit--
        }

        // Update the score display
        basicScore.text = "Score: $score"

        // Reset UI after a brief delay
        Handler().postDelayed({
            resetUI()
            loadNextQuestion()
        }, 1000)
    }

    private fun showToast(message: String) {
        Toast.makeText(this@BasicLevel, message, Toast.LENGTH_SHORT).show()
    }

    private fun highlightAnswer(isCorrect: Boolean) {
        val selectedRadioButton: RadioButton = findViewById(optionsRadioButton.checkedRadioButtonId)
        selectedRadioButton.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)
    }

    private fun resetUI() {
        opt1.setBackgroundResource(R.drawable.radio_button_background)
        opt2.setBackgroundResource(R.drawable.radio_button_background)
        opt3.setBackgroundResource(R.drawable.radio_button_background)
        opt4.setBackgroundResource(R.drawable.radio_button_background)
    }

    private fun setQuestion(mcqItem: McqItem) {
        val basicQuestion: TextView = findViewById(R.id.basicQuestion)
        basicQuestion.text = mcqItem.question
        opt1.text = mcqItem.options[0]
        opt2.text = mcqItem.options[1]
        opt3.text = mcqItem.options[2]
        opt4.text = mcqItem.options[3]
    }

    private fun showQuitConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Are you sure you want to quit the quiz?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                onBackPressed()
                usedQuestions.clear()
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
