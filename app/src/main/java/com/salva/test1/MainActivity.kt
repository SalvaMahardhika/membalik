package com.salva.test1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var scoreTeamA = 0
    private var scoreTeamB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val scoreATextView = findViewById<TextView>(R.id.scoreA)
        val scoreBTextView = findViewById<TextView>(R.id.scoreB)

        val buttonA1 = findViewById<Button>(R.id.buttonA1)
        val buttonA2 = findViewById<Button>(R.id.buttonA2)
        val buttonB1 = findViewById<Button>(R.id.buttonB1)
        val buttonB2 = findViewById<Button>(R.id.buttonB2)
        val resetButton = findViewById<Button>(R.id.resetButton)

        buttonA1.setOnClickListener {
            scoreTeamA += 1
            scoreATextView.text = scoreTeamA.toString()
        }

        buttonA2.setOnClickListener {
            scoreTeamA += 2
            scoreATextView.text = scoreTeamA.toString()
        }

        buttonB1.setOnClickListener {
            scoreTeamB += 1
            scoreBTextView.text = scoreTeamB.toString()
        }

        buttonB2.setOnClickListener {
            scoreTeamB += 2
            scoreBTextView.text = scoreTeamB.toString()
        }

        resetButton.setOnClickListener {
            scoreTeamA = 0
            scoreTeamB = 0
            scoreATextView.text = scoreTeamA.toString()
            scoreBTextView.text = scoreTeamB.toString()
        }
    }
}