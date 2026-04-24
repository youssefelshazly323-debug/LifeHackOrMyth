package com.example.lifehackormyth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 5)

        findViewById<TextView>(R.id.tvScore).text = "You scored $score out of $total"

        val feedback = when {
            score == total -> "Master Hacker!"
            score >= total / 2 -> "Good job! Keep learning! "
            else -> "Stay Safe Online! Keep practising! "
        }
        findViewById<TextView>(R.id.tvScoreFeedback).text = feedback

        // Review button - shows a simple list
        findViewById<Button>(R.id.btnReview).setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        // Play again
        findViewById<Button>(R.id.btnPlayAgain).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}