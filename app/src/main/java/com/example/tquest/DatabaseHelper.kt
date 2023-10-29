package com.example.tquest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "McqItems.db"
        const val TABLE_NAME = "mcq_items"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_OPTIONS = "options"
        const val COLUMN_CORRECT_ANSWER_INDEX = "correct_answer_index"
    }

    private val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME (" +
            "_id INTEGER PRIMARY KEY," +
            "$COLUMN_QUESTION TEXT," +
            "$COLUMN_OPTIONS TEXT," +
            "$COLUMN_CORRECT_ANSWER_INDEX INTEGER)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database schema upgrades
    }

    fun insertQuestionWithOptions(question: String, options: List<String>, correctAnswer: String) {
        val db = this.writableDatabase
        val optionsString = options.joinToString("|") // Convert options list to a pipe-separated string
        val values = ContentValues().apply {
            put(COLUMN_QUESTION, question)
            put(COLUMN_OPTIONS, optionsString)
            put(COLUMN_CORRECT_ANSWER_INDEX, correctAnswer)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

}
