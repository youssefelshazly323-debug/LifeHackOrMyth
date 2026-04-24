package com.example.lifehackormyth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FlashcardActivity : AppCompatActivity() {

    // Our list of questions: Triple(question, isHack, explanation)
    val questions = listOf(
        Triple("Putting a phone in rice fixes water damage.", false, "Myth! Rice doesn't absorb water fast enough. Use silica gel or a repair shop."),
        Triple("Drinking water before a meal helps you eat less.", true, "Hack! Studies show it reduces calorie intake."),
        Triple("We only use 10% of our brains.", false, "Myth! Brain scans show we use virtually all of our brain."),
        Triple("Chewing gum boosts concentration and memory.", true, "Hack! Research shows it increases blood flow to the brain."),
        Triple("Reading in dim light permanently damages your eyesight.", false, "Myth! It may strain your eyes temporarily but causes no permanent damage.")
    )

    var currentIndex = 0
    var score = 0
    val userAnswers = mutableListOf<Boolean>() // track user answers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        displayQuestion()

        findViewById<Button>(R.id.btnHack).setOnClickListener { checkAnswer(true) }
        findViewById<Button>(R.id.btnMyth).setOnClickListener { checkAnswer(false) }
        findViewById<Button>(R.id.btnNext).setOnClickListener { nextQuestion() }
    }

    @SuppressLint("SetTextI18n")
    fun displayQuestion() {
        val q = questions[currentIndex]
        findViewById<TextView>(R.id.tvQuestion).text = q.first
        findViewById<TextView>(R.id.tvQuestionNumber).text =
            "Question ${currentIndex + 1} of ${questions.size}"
        findViewById<TextView>(R.id.tvFeedback).text = ""
        findViewById<Button>(R.id.btnNext).visibility = View.GONE
        findViewById<Button>(R.id.btnHack).isEnabled = true
        findViewById<Button>(R.id.btnMyth).isEnabled = true
    }

    @SuppressLint("SetTextI18n")
    fun checkAnswer(userSaidHack: Boolean) {
        val correct = questions[currentIndex].second
        val explanation = questions[currentIndex].third
        val feedback = findViewById<TextView>(R.id.tvFeedback)

        userAnswers.add(userSaidHack)

        if (userSaidHack == correct) {
            score++
            feedback.text = "Correct! That's a real time-saver!\n$explanation"
            feedback.setTextColor(android.graphics.Color.GREEN)
        } else {
            feedback.text = "Wrong! That's just an urban myth.\n$explanation"
            feedback.setTextColor(android.graphics.Color.RED)
        }

        // Disable buttons after answering
        findViewById<Button>(R.id.btnHack).isEnabled = false
        findViewById<Button>(R.id.btnMyth).isEnabled = false
        findViewById<Button>(R.id.btnNext).visibility = View.VISIBLE
    }

    fun nextQuestion() {
        currentIndex++
        if (currentIndex < questions.size) {
            displayQuestion()
        } else {
            // Go to Score Screen
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL", questions.size)
            startActivity(intent)
            finish()
        }
    }
}