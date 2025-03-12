package com.salva.test1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var scoreTeamA = 0
    private var scoreTeamB = 0
    private lateinit var scoreTeamATextView: TextView
    private lateinit var scoreTeamBTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var btnPlus1TeamA: Button
    private lateinit var btnPlus2TeamA: Button
    private lateinit var btnPlus1TeamB: Button
    private lateinit var btnPlus2TeamB: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi UI
        scoreTeamATextView = findViewById(R.id.score_team_a)
        scoreTeamBTextView = findViewById(R.id.score_team_b)
        resultTextView = findViewById(R.id.result_text)
        btnPlus1TeamA = findViewById(R.id.btn_plus1_team_a)
        btnPlus2TeamA = findViewById(R.id.btn_plus2_team_a)
        btnPlus1TeamB = findViewById(R.id.btn_plus1_team_b)
        btnPlus2TeamB = findViewById(R.id.btn_plus2_team_b)
        btnReset = findViewById(R.id.btn_reset)

        // Restore skor jika layar diputar
        savedInstanceState?.let {
            scoreTeamA = it.getInt("scoreTeamA", 0)
            scoreTeamB = it.getInt("scoreTeamB", 0)
            updateScores()
        }

        // Event klik tombol
        btnPlus1TeamA.setOnClickListener { updateScore(1, "A") }
        btnPlus2TeamA.setOnClickListener { updateScore(2, "A") }
        btnPlus1TeamB.setOnClickListener { updateScore(1, "B") }
        btnPlus2TeamB.setOnClickListener { updateScore(2, "B") }

        btnReset.setOnClickListener {
            scoreTeamA = 0
            scoreTeamB = 0
            resultTextView.text = ""
            enableButtons(true)
            updateScores()
        }
    }

    // Simpan data saat layar diputar
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("scoreTeamA", scoreTeamA)
        outState.putInt("scoreTeamB", scoreTeamB)
    }

    // Fungsi untuk memperbarui skor
    private fun updateScore(points: Int, team: String) {
        if (team == "A") {
            scoreTeamA += points
        } else {
            scoreTeamB += points
        }

        updateScores()
        checkWinner()
    }

    // Periksa apakah ada pemenang
    private fun checkWinner() {
        if ((scoreTeamA >= 21 && scoreTeamA >= scoreTeamB + 2) || scoreTeamA == 30) {
            resultTextView.text = "Team A Wins!"
            enableButtons(false)
        } else if ((scoreTeamB >= 21 && scoreTeamB >= scoreTeamA + 2) || scoreTeamB == 30) {
            resultTextView.text = "Team B Wins!"
            enableButtons(false)
        }
    }

    // Perbarui tampilan skor
    private fun updateScores() {
        scoreTeamATextView.text = scoreTeamA.toString()
        scoreTeamBTextView.text = scoreTeamB.toString()
    }

    // Aktifkan/nonaktifkan tombol setelah game selesai
    private fun enableButtons(enable: Boolean) {
        btnPlus1TeamA.isEnabled = enable
        btnPlus2TeamA.isEnabled = enable
        btnPlus1TeamB.isEnabled = enable
        btnPlus2TeamB.isEnabled = enable
    }
}
