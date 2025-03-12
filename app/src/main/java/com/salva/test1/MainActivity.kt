package com.salva.test1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var scoreTeamA: TextView
    private lateinit var scoreTeamB: TextView
    private lateinit var resultText: TextView
    private lateinit var btnPlus1A: Button
    private lateinit var btnPlus2A: Button
    private lateinit var btnPlus1B: Button
    private lateinit var btnPlus2B: Button
    private lateinit var btnReset: Button

    private var scoreA = 0
    private var scoreB = 0
    private val winningScore = 21  // Skor kemenangan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi View
        scoreTeamA = findViewById(R.id.score_team_a)
        scoreTeamB = findViewById(R.id.score_team_b)
        resultText = findViewById(R.id.result_text)
        btnPlus1A = findViewById(R.id.btn_plus1_team_a)
        btnPlus2A = findViewById(R.id.btn_plus2_team_a)
        btnPlus1B = findViewById(R.id.btn_plus1_team_b)
        btnPlus2B = findViewById(R.id.btn_plus2_team_b)
        btnReset = findViewById(R.id.btn_reset)

        // Tambahkan event listener ke tombol
        btnPlus1A.setOnClickListener { updateScore("A", 1) }
        btnPlus2A.setOnClickListener { updateScore("A", 2) }
        btnPlus1B.setOnClickListener { updateScore("B", 1) }
        btnPlus2B.setOnClickListener { updateScore("B", 2) }
        btnReset.setOnClickListener { resetGame() }
    }

    // Fungsi untuk memperbarui skor
    private fun updateScore(team: String, points: Int) {
        if (scoreA >= winningScore || scoreB >= winningScore) return // Jika ada pemenang, tombol dinonaktifkan

        if (team == "A") {
            scoreA += points
            scoreTeamA.text = scoreA.toString()
        } else {
            scoreB += points
            scoreTeamB.text = scoreB.toString()
        }

        checkWinner()
    }

    // Fungsi untuk mengecek pemenang
    private fun checkWinner() {
        when {
            scoreA >= winningScore -> {
                showWinnerDialog("Team A")
            }
            scoreB >= winningScore -> {
                showWinnerDialog("Team B")
            }
        }
    }

    // Menampilkan dialog pemenang
    private fun showWinnerDialog(winner: String) {
        resultText.text = "$winner Wins!"
        resultText.visibility = TextView.VISIBLE

        // Tampilkan Alert Dialog
        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage("$winner telah menang dengan skor $winningScore!")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()

        // Matikan tombol jika ada pemenang
        disableButtons()
    }

    // Menonaktifkan tombol tambah skor
    private fun disableButtons() {
        btnPlus1A.isEnabled = false
        btnPlus2A.isEnabled = false
        btnPlus1B.isEnabled = false
        btnPlus2B.isEnabled = false
    }

    // Reset permainan
    private fun resetGame() {
        scoreA = 0
        scoreB = 0
        scoreTeamA.text = "0"
        scoreTeamB.text = "0"
        resultText.text = ""
        resultText.visibility = TextView.GONE

        // Aktifkan kembali tombol
        btnPlus1A.isEnabled = true
        btnPlus2A.isEnabled = true
        btnPlus1B.isEnabled = true
        btnPlus2B.isEnabled = true
    }
}
