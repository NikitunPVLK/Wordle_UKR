package com.example.wordle_ukr.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordle_ukr.ui.screens.game_screen.GameScreen
import com.example.wordle_ukr.ui.screens.game_screen.components.DifficultyLevelAlertDialog
import com.example.wordle_ukr.ui.theme.WordleUkrTheme
import com.example.wordle_ukr.ui.view_models.GameViewModel
import com.example.wordle_ukr.ui.view_models.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleUkrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val gameViewModel: GameViewModel = viewModel(
                        factory = ViewModelFactory(
                            applicationContext.resources
                        )
                    )
                    var showDialog by remember {
                        mutableStateOf(true)
                    }
                    if (showDialog) {
                        DifficultyLevelAlertDialog(
                            onDifficultyLevelSelected = {
                                gameViewModel.onDifficultyLevelSelected(it)
                                showDialog = false
                            }
                        )
                    }
                    val gameField by gameViewModel.gameField.collectAsState()
                    val helperText by gameViewModel.helperText.collectAsState()
                    GameScreen(
                        gameField,
                        helperText,
                        gameViewModel::onKeyClicked,
                        gameViewModel::onDeleteClicked,
                        gameViewModel::onSubmitClicked,
                        gameViewModel::onHintClicked
                    )
                }
            }
        }
    }
}