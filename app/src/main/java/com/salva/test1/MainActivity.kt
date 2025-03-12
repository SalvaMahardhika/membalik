package com.salva.test1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
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

    // Inisialisasi ViewModel
    private val scoreViewModel: ScoreViewModel by viewModels()

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

        // Mengamati perubahan skor Team A
        scoreViewModel.scoreA.observe(this) { score ->
            scoreTeamA.text = score.toString()
        }

        // Mengamati perubahan skor Team B
        scoreViewModel.scoreB.observe(this) { score ->
            scoreTeamB.text = score.toString()
        }

        // Mengamati pemenang
        scoreViewModel.winner.observe(this) { winner ->
            if (winner != null) {
                showWinnerDialog(winner)
                disableButtons()
            }
        }

        // Event listener tombol
        btnPlus1A.setOnClickListener { scoreViewModel.updateScore("A", 1) }
        btnPlus2A.setOnClickListener { scoreViewModel.updateScore("A", 2) }
        btnPlus1B.setOnClickListener { scoreViewModel.updateScore("B", 1) }
        btnPlus2B.setOnClickListener { scoreViewModel.updateScore("B", 2) }
        btnReset.setOnClickListener { resetGame() }
    }

    // Menampilkan dialog jika ada pemenang
    private fun showWinnerDialog(winner: String) {
        resultText.text = "$winner Wins!"
        resultText.visibility = TextView.VISIBLE

        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage("$winner menang dengan skor 21!")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    // Menonaktifkan tombol saat ada pemenang
    private fun disableButtons() {
        btnPlus1A.isEnabled = false
        btnPlus2A.isEnabled = false
        btnPlus1B.isEnabled = false
        btnPlus2B.isEnabled = false
    }

    // Reset permainan
    private fun resetGame() {
        scoreViewModel.resetGame()
        resultText.text = ""
        resultText.visibility = TextView.GONE

        btnPlus1A.isEnabled = true
        btnPlus2A.isEnabled = true
        btnPlus1B.isEnabled = true
        btnPlus2B.isEnabled = true
    }
}
