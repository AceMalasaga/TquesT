package com.example.tquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuButton: ImageView
    private var isExiting: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        menuButton = findViewById(R.id.menu)

        // Set click listener for the menu icon
        menuButton.setOnClickListener {
            // Open/close the navigation drawer when the menu icon is clicked
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        val navView : NavigationView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener { menuItem->
            when (menuItem.itemId){
                R.id.nav_create_basic->{
                    val intent= Intent(this, CreateBasic::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_board->{
                    val intent = Intent(this, Board::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_New_Quest->{
                    val intent = Intent(this, CreateIntermediate::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_list->{
                    val intent = Intent(this, CreateHard::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_exit->{
                    isExiting = true
                    showExitConfirmationDialog()
                    true
                }
                else->false
            }
        }


        // handle the card view and if it click redirect to the difficulty page
        val basicLevel : CardView = findViewById(R.id.basicLevel)

        basicLevel.setOnClickListener{
            val intent = Intent(this, BasicPage::class.java)
            startActivity(intent)
        }

        val intermediateLevel : CardView = findViewById(R.id.intermediateLevelCardView)

        intermediateLevel.setOnClickListener{
            val intent = Intent(this, InterPage::class.java)
            startActivity(intent)
        }

        val hardLevelCardView : CardView = findViewById(R.id.hardLevelCardView)

        hardLevelCardView.setOnClickListener {
            val intent = Intent(this, HardPage::class.java)
            startActivity(intent)
        }
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                // Reset shared preferences values to 0
                val editorBasic = applicationContext.getSharedPreferences("scoreBasic", MODE_PRIVATE).edit()
                editorBasic.putInt("Score", 0)
                editorBasic.apply()

                val editorInter = applicationContext.getSharedPreferences("scoreInter", MODE_PRIVATE).edit()
                editorInter.putInt("ScoreInter", 0)
                editorInter.apply()

                val editorHard = applicationContext.getSharedPreferences("scoreHard", MODE_PRIVATE).edit()
                editorHard.putInt("ScoreHard", 0)
                editorHard.apply()

                // If user clicks "Yes," close the app
                finishAffinity()
                exitProcess(0)
            }
            .setNegativeButton("No") { _, _ ->
                // If user clicks "No," dismiss the dialog (do nothing)
            }
            .create()
            .show()
    }
}

