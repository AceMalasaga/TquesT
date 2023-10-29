package com.example.tquest

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

data class fill(
    val question : String,
    val correctAnswer : String
)

class HardLevel : AppCompatActivity() {
    //global variable ui
    private lateinit var hardQuestion : TextView
    private lateinit var inputHard : EditText
    private lateinit var scoreHard : TextView
    private lateinit var countdownHard : TextView
    private lateinit var btnNextHard: Button
    private var nextButtonClickedCountHard = 0

    private var score = 0
    private var questionLimit = 10
    private val usedQuestion : MutableList<fill> = mutableListOf()
    private lateinit var currentQuestion : fill

    private val hardQuestionLevel = arrayOf(
        //20 question
        fill("public class Increment {\n" +
                "    public static void main(String[] args) {\n" +
                "        int g = 3;\n" +
                "        System.out.print(++g * 8);\n" +
                "    }\n" +
                "}\n","32"),
        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        int arr[] = {1, 2, 3, 4, 5};\n" +
                "        for (int i = 0; i < arr.length - 2; ++i) {\n" +
                "            System.out.print(arr[i] + \" \");\n" +
                "        }\n" +
                "    }\n" +
                "}\n","1 2 3"),
        fill("public class String_demo {\n" +
                "    public static void main(String[] args) {\n" +
                "        char chars[] = {'a', 'b', 'c'};\n" +
                "        String s = new String(chars);\n" +
                "        System.out.println(s);\n" +
                "    }\n" +
                "}\n","abc"),
        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        StringBuffer s1 = new StringBuffer(\"Quiz\");\n" +
                "        StringBuffer s2 = s1.reverse();\n" +
                "        System.out.print(s2);\n" +
                "    }\n" +
                "}\n","ziuQ"),
        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        Integer i = new Integer(257);\n" +
                "        byte x = i.byteValue();\n" +
                "        System.out.print(x);\n" +
                "    }\n" +
                "}\n","1"),

        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        double x = 3.14;\n" +
                "        int y = (int) Math.ceil(x);\n" +
                "        System.out.print(y);\n" +
                "    }\n" +
                "}\n","4"),
        fill("import java.util.ArrayList;\n" +
                "\n" +
                "public class ArrayLists {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<String> obj = new ArrayList<>();\n" +
                "        obj.add(\"A\");\n" +
                "        obj.add(\"B\");\n" +
                "        obj.add(\"C\");\n" +
                "        obj.add(1, \"D\");\n" +
                "        System.out.println(obj);\n" +
                "    }\n" +
                "}\n","[A, D, B, C]"),
        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        String chars[] = {\"a\", \"b\", \"c\", \"a\", \"c\"};\n" +
                "        for (int i = 0; i < chars.length; ++i) {\n" +
                "            for (int j = i + 1; j < chars.length; ++j) {\n" +
                "                if (chars[i].compareTo(chars[j]) == 0) {\n" +
                "                    System.out.print(chars[j]);\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n","ac",),
        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        Integer i = new Integer(257);\n" +
                "        float x = i.floatValue();\n" +
                "        System.out.print(x);\n" +
                "    }\n" +
                "}\n","257.0"),

        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        Long i = new Long(256);\n" +
                "        System.out.print(i.hashCode());\n" +
                "    }\n" +
                "}\n","256"),
        fill("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        double d = Math.round(2.5 + Math.random());\n" +
                "        System.out.println(d);\n" +
                "    }\n" +
                "}\n","3.0"),
        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        double x = 3.1;\n" +
                "        double y = 4.5;\n" +
                "        double z = Math.max(x, y);\n" +
                "        System.out.print(z);\n" +
                "    }\n" +
                "}\n","4.5"),
        fill("class Alligator {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[][] x = {{1, 2}, {3, 4, 5}, {6, 7, 8, 9}};\n" +
                "        int[][] y = x;\n" +
                "        System.out.println(y[2][1]);\n" +
                "    }\n" +
                "}\n","7"),
        fill("public class Output {\n" +
                "    public static void main(String[] args) {\n" +
                "        int x, y = 1;\n" +
                "        x = 10;\n" +
                "        if (x != 10 && x / 2 == 0) {\n" +
                "            System.out.println(y);\n" +
                "        } else {\n" +
                "            System.out.println(++y);\n" +
                "        }\n" +
                "    }\n" +
                "}\n","2"),
        fill("class ArrayOutput {\n" +
                "    public static void main(String[] args) {\n" +
                "        char array_variable[] = new char[10];\n" +
                "        for (int i = 0; i < 10; ++i) {\n" +
                "            array_variable[i] = 'i'; \n" +
                "            System.out.print(array_variable[i] + \" \");\n" +
                "        }\n" +
                "    }\n" +
                "}\n","iiiiiiiiii"),
        fill("class MainClass {\n" +
                "    public static void main(String[] args) {\n" +
                "        char a = 'A';\n" +
                "        a++;\n" +
                "        System.out.print((int) a);\n" +
                "    }\n" +
                "}\n","66"),
        fill("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        for (int i = 3; i <= 4; i++) {\n" +
                "            for (int j = 2; j < i; j++) {\n" +
                "                System.out.print(\"\");\n" +
                "            }\n" +
                "            System.out.print(\"WIN\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n","WINWIN"),
        fill("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int i;\n" +
                "        for (i = 5; i > 10; i++) {\n" +
                "            System.out.println(i);\n" +
                "        }\n" +
                "        System.out.println(i * 4);\n" +
                "    }\n" +
                "}\n","20"),
        fill("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int i;\n" +
                "        for (i = 5; i >= 1; i--) {\n" +
                "            if (i % 2 == 1)\n" +
                "                continue;\n" +
                "            System.out.print(i + \" \");\n" +
                "        }\n" +
                "    }\n" +
                "}\n","4 2"),
        fill("public class Test\n" +
                "{\n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        int i = 0;\n" +
                "        for (System.out.println(\"HI\"); i < 1; i++)\n" +
                "        {\n" +
                "            System.out.println(\"HELLO GEEKS\");\n" +
                "        }\n" +
                "    }\n" +
                "}","HI\n" +
                "HELLO GEEKS")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_level)

        countdownHard = findViewById(R.id.countdownTimerHard)
        hardQuestion = findViewById(R.id.hardQuestion)
        inputHard = findViewById(R.id.inputHard)
        scoreHard = findViewById(R.id.scoreHard)

        btnNextHard = findViewById(R.id.btnNextHard)
        btnNextHard.setOnClickListener {
            if (nextButtonClickedCountHard < 5) {
                loadNextQuestion()
                nextButtonClickedCountHard++
            } else {
                showToast("You have reached the maximum limit of the next.")
                btnNextHard.isEnabled = false // Disable the button after reaching the limit
            }
        }

        val btnSubmitHard : Button = findViewById(R.id.btnSubmitHard)
        btnSubmitHard.setOnClickListener {
            handleAnswerSubmission()
        }

        val imgBackHardMain : ImageView = findViewById(R.id.imgBackHardMain)
        imgBackHardMain.setOnClickListener{
            showQuitConfirmationDialog()
        }
        startTimer()
        loadNextQuestion()
    }

    private fun startTimer(){
        object : CountDownTimer(3600000, 1000) { // 900000 milliseconds = 15 minutes
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                countdownHard.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                countdownHard.text = "Time's up!"
                handleAnswerSubmission()
                loadNextQuestion()
            }
        }.start()
    }

    private fun loadNextQuestion(){
        if (questionLimit > 0){
            var newQuestion  : fill
            do {
                newQuestion = hardQuestionLevel.random()
            } while (usedQuestion.contains(newQuestion))

            usedQuestion.add(newQuestion)
            currentQuestion = newQuestion
            setQuestion(currentQuestion)
            inputHard.text.clear()
            //questionLimit--
        }
        else{
            val sharedPreferences = getSharedPreferences("scoreHard", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("ScoreHard", score)
            editor.apply()

            showToast("End of the Quiz!")
            val intent = Intent(this,Board::class.java)
            intent.putExtra("ScoreHard", score)
            startActivity(intent)
        }
    }

    private fun handleAnswerSubmission(){
        if (countdownHard.text.toString() == "Time's up!"){
            val sharedPreferences = getSharedPreferences("scoreHard", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("ScoreHard", score)
            editor.apply()

            Handler().postDelayed({
                val intent = Intent(this, Board::class.java)
                startActivity(intent)
                finish() // Finish the current activity to prevent going back to it
            }, 2000) // 3000 milliseconds = 3 seconds delay

            return
        }

        val userAnswer = inputHard.text.toString().trim()
        val correctAnswer = currentQuestion.correctAnswer

        if (userAnswer.isEmpty()) {
            showToast("Please select an answer!")
            return
        }

        val isAnswerCorrect = userAnswer.equals(correctAnswer,ignoreCase = true)

        showToast(if (isAnswerCorrect)"Correct Answer!" else "Incorrect Answer!")

        if (isAnswerCorrect){
            score++
        }

        if (questionLimit > 0){
            questionLimit--
        }
        scoreHard.text= "Score: $score"

        Handler().postDelayed({
            loadNextQuestion()
            resetUI()
        },1000)
    }

    private fun setQuestion(questionHard: fill) {
        val hardQuestion : TextView = findViewById(R.id.hardQuestion)
        hardQuestion.text = questionHard.question
    }
    private fun showToast(message: String) {
        Toast.makeText(this@HardLevel, message, Toast.LENGTH_SHORT).show()
    }

    private fun resetUI() {
        inputHard.text.clear()
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