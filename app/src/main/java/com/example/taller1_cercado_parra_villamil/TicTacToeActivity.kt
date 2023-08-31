package com.example.taller1_cercado_parra_villamil;

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.taller1_cercado_parra_villamil.databinding.ActivityTicTacToeBinding

class TicTacToeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicTacToeBinding
    private val handler = Handler()

    // Lateint para declarar variables.
    private lateinit var buttons: Array<Button>
    private lateinit var currentPlayer: String
    private lateinit var gameBoard: Array<String>
    private lateinit var player1TextView: TextView
    private lateinit var player2TextView: TextView
    private var gameEnded = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Arreglo de botones con los 9 botones creados.
        buttons = arrayOf(
            findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3), findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6), findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9))
        reinicioColores()
        player1TextView = findViewById(R.id.player1TextView)
        player2TextView = findViewById(R.id.player2TextView)
        currentPlayer = "X"
        gameBoard = Array(9) { "" }
        updatePlayerTurn()
        for (button in buttons) {
            button.setOnClickListener { onButtonClick(it) } }
        //Nueva partida.
        findViewById<Button>(R.id.newGameButton).setOnClickListener { newGame() }
        gameEnded = false
    }
    // Función para Comprobar ganador.
    private fun onButtonClick(view: View) {
        if (gameEnded) return
        val button = view as Button
        val index = buttons.indexOf(button)
        if (gameBoard[index].isEmpty()) {
            gameBoard[index] = currentPlayer
            button.text = currentPlayer
            if (checkWin(currentPlayer)) {
                gameEnded = true
                showWinnerAlert(currentPlayer)
            } else if (isBoardFull()) {
                gameEnded = true
                showDrawAlert()
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                updatePlayerTurn()
            }
        }
    }
    // Función para ir comprobar victorias.
    private fun checkWin(player: String): Boolean {
        //Filas.
        for (i in 0..6 step 3) {
            if (gameBoard[i] == player && gameBoard[i + 1] == player && gameBoard[i + 2] == player) {
                colorVictoria(listOf(i, i + 1, i + 2))
                return true
            }
        }
        //Columnas.
        for (i in 0..2) {
            if (gameBoard[i] == player && gameBoard[i + 3] == player && gameBoard[i + 6] == player) {
                colorVictoria(listOf(i, i + 3, i + 6))
                return true
            }
        }
        //Diagonales.
        if (gameBoard[0] == player && gameBoard[4] == player && gameBoard[8] == player) {
            colorVictoria(listOf(0, 4, 8))
            return true
        }
        if (gameBoard[2] == player && gameBoard[4] == player && gameBoard[6] == player) {
            colorVictoria(listOf(2, 4, 6))
            return true
        }
        return false //Empate.
    }

    private fun isBoardFull(): Boolean {
        return gameBoard.all { it.isNotEmpty() }
    }
    //Textviews de cada turnp).
    private fun updatePlayerTurn() {
        player1TextView.text = "Jugador 1 [X]"
        player2TextView.text = "Jugador 2 [O]"
        if (currentPlayer == "X") {
            player1TextView.text = "Turno del Jugador 1!"
        } else {
            player2TextView.text = "Turno del Jugador 2!"
        }
    }
    // Función para reiniciar el tablero del arreglo con los 9 botones.
    private fun newGame() {
        gameEnded = false
        reinicioColores()
        for (i in 0 until gameBoard.size) {
            gameBoard[i] = ""
            buttons[i].text = ""
        }
        currentPlayer = "X"
        updatePlayerTurn()
    }
    // Mostrar empates
    private fun showWinnerAlert(winner: String) {
        val message = if (winner == "X") "El jugador 1 gana!" else "El jugador 2 gana!"
        handler.postDelayed({
            AlertDialog.Builder(this)
                .setTitle("Fin del juego!")
                .setMessage(message)
                .setPositiveButton("Nueva Partida") { _, _ -> newGame() }
                .setCancelable(false)
                .show()
        }, 900)
    }
    // Mensaje de empate.
    private fun showDrawAlert() {
        handler.postDelayed({
            AlertDialog.Builder(this)
                .setTitle("Fin del juego")
                .setMessage("Empate!")
                .setPositiveButton("Nueva Partida") { _, _ -> newGame() } //Por lo que leí esto es una función anonima y ya que no hacemos uso de sus 2 parametros, entonces solo ponemos guiones.
                .setCancelable(false)
                .show()
        }, 900)
    }
    // Cambiar colores.
    private fun colorVictoria(winningPositions: List<Int>) {
        for (position in winningPositions) {
            buttons[position].setBackgroundColor(ContextCompat.getColor(this, R.color.Win))
        }
    }
    // Reiniciar colores.
    private fun reinicioColores() {
        for (button in buttons) {
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.AzulFondo))
        }
    }
}