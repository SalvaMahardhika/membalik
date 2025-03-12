package com.salva.test1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    private val _scoreA = MutableLiveData(0)  // LiveData untuk skor Team A
    private val _scoreB = MutableLiveData(0)  // LiveData untuk skor Team B
    private val _winner = MutableLiveData<String?>() // LiveData untuk menang

    val scoreA: LiveData<Int> = _scoreA
    val scoreB: LiveData<Int> = _scoreB
    val winner: LiveData<String?> = _winner

    private val winningScore = 21

    // Fungsi untuk menambah skor
    fun updateScore(team: String, points: Int) {
        if (_winner.value != null) return  // Jika sudah ada pemenang, hentikan update

        if (team == "A") {
            _scoreA.value = (_scoreA.value ?: 0) + points
            checkWinner()
        } else {
            _scoreB.value = (_scoreB.value ?: 0) + points
            checkWinner()
        }
    }

    // Mengecek apakah ada yang menang
    private fun checkWinner() {
        when {
            (_scoreA.value ?: 0) >= winningScore -> _winner.value = "Team A"
            (_scoreB.value ?: 0) >= winningScore -> _winner.value = "Team B"
        }
    }

    // Reset skor
    fun resetGame() {
        _scoreA.value = 0
        _scoreB.value = 0
        _winner.value = null
    }
}
