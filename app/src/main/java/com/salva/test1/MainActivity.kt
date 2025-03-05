package com.example.scorekeeper

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var scoreTeamA = 0
    private var scoreTeamB = 0
    private lateinit var scoreTeamATextView: TextView
    private lateinit var scoreTeamBTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi UI
        scoreTeamATextView = findViewById(R.id.score_team_a)
        scoreTeamBTextView = findViewById(R.id.score_team_b)
        val btnPlus1TeamA: Button = findViewById(R.id.btn_plus1_team_a)
        val btnPlus2TeamA: Button = findViewById(R.id.btn_plus2_team_a)
        val btnPlus1TeamB: Button = findViewById(R.id.btn_plus1_team_b)
        val btnPlus2TeamB: Button = findViewById(R.id.btn_plus2_team_b)
        val btnReset: Button = findViewById(R.id.btn_reset)

        // Restore skor jika layar diputar
        savedInstanceState?.let {
            scoreTeamA = it.getInt("scoreTeamA", 0)
            scoreTeamB = it.getInt("scoreTeamB", 0)
            updateScores()
        }

        // Event klik tombol
        btnPlus1TeamA.setOnClickListener {
            scoreTeamA += 1
            updateScores()
        }

        btnPlus2TeamA.setOnClickListener {
            scoreTeamA += 2
            updateScores()
        }

        btnPlus1TeamB.setOnClickListener {
            scoreTeamB += 1
            updateScores()
        }

        btnPlus2TeamB.setOnClickListener {
            scoreTeamB += 2
            updateScores()
        }

        btnReset.setOnClickListener {
            scoreTeamA = 0
            scoreTeamB = 0
            updateScores()
        }
    }

    // Simpan data saat layar diputar
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("scoreTeamA", scoreTeamA)
        outState.putInt("scoreTeamB", scoreTeamB)
    }

    // Update tampilan skor
    private fun updateScores() {
        scoreTeamATextView.text = scoreTeamA.toString()
        scoreTeamBTextView.text = scoreTeamB.toString()
    }
}
