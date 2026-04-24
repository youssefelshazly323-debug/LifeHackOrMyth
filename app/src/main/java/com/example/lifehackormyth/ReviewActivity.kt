package com.example.lifehackormyth
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    val questions = listOf(
        Triple("Putting a phone in rice fixes water damage.", false, "Myth! Rice doesn't absorb water fast enough."),
        Triple("Drinking water before a meal helps you eat less.", true, "Hack! Studies show it reduces calorie intake."),
        Triple("We only use 10% of our brains.", false, "Myth! Brain scans show we use virtually all of our brain."),
        Triple("Chewing gum boosts concentration and memory.", true, "Hack! Research shows it increases blood flow to the brain."),
        Triple("Reading in dim light permanently damages your eyesight.", false, "Myth! It may strain eyes temporarily but causes no permanent damage.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val sb = StringBuilder()
        questions.forEachIndexed { i, q ->
            val label = if (q.second) "LIFE HACK" else "URBAN MYTH"
            sb.append("${i + 1}. ${q.first}\n")
            sb.append("   Answer: $label\n")
            sb.append("   ${q.third}\n\n")
        }

        findViewById<TextView>(R.id.tvReviewContent).text = sb.toString()
    }
}