package com.ptut.canvasexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ptut.canvasexample.gender_picker.GenderPickerScreen
import com.ptut.canvasexample.pathBasics.PathEffectsScreen
import com.ptut.canvasexample.tictactoe.Player
import com.ptut.canvasexample.tictactoe.TicTacToe

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            GenderPickerScreen(
//                modifier = Modifier.fillMaxSize()
//            )
            var winningPlayer by remember {
                mutableStateOf<Player?>(null)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TicTacToe(
                    onNewRound = {
                        winningPlayer = null
                    },
                    onPlayerWin = {
                        winningPlayer = it
                    }
                )
                Spacer(modifier = Modifier.height(50.dp))

                winningPlayer?.let {
                    Text(
                        text = "Player ${it.symbol} has won!",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
