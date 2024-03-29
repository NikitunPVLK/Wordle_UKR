package com.example.wordle_ukr.ui.screens.game_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle_ukr.R
import com.example.wordle_ukr.ui.models.Letter
import com.example.wordle_ukr.ui.screens.game_screen.components.GameField
import com.example.wordle_ukr.ui.screens.game_screen.components.Keyboard
import com.example.wordle_ukr.ui.theme.Inter

@Composable
fun GameScreen(
    gameField: List<List<Letter>>,
    helperText: String,
    onKeyClicked: (String) -> Unit,
    onDeleteClicked: () -> Unit,
    onSubmitClicked: () -> Unit,
    onHintClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = stringResource(id = R.string.app_name_ua),
            fontFamily = Inter,
            fontSize = 46.sp,
            fontWeight = FontWeight.ExtraBold
        )
        GameField(gameField)
        Text(
            text = helperText,
            fontSize = 30.sp,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold
        )
        Keyboard(
            stringResource(id = R.string.alphabet),
            onKeyClicked,
            onDeleteClicked,
            onSubmitClicked
        )
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            onClick = onHintClicked
        ) {
            Text(
                text = stringResource(id = R.string.hint),
                fontSize = 30.sp,
                color = Color.White,
                fontFamily = Inter,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(emptyList(), "Test", {}, {}, {}, {})
}