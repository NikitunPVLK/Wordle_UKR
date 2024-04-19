package com.example.wordle_ukr.ui.screens.game_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle_ukr.R
import com.example.wordle_ukr.ui.models.DifficultyLevel
import com.example.wordle_ukr.ui.models.Letter
import com.example.wordle_ukr.ui.screens.game_screen.components.DifficultyLevelAlertDialog
import com.example.wordle_ukr.ui.screens.game_screen.components.GameField
import com.example.wordle_ukr.ui.screens.game_screen.components.Keyboard
import com.example.wordle_ukr.ui.theme.Inter
import com.example.wordle_ukr.ui.theme.TopButtonsBackground

@Composable
fun GameScreen(
    gameField: List<List<Letter>>,
    helperText: String,
    onKeyClicked: (String) -> Unit,
    onDeleteClicked: () -> Unit,
    onSubmitClicked: () -> Unit,
    onHintClicked: () -> Unit,
    onDifficultyLevelSelected: (DifficultyLevel, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    if (showDialog) {
        DifficultyLevelAlertDialog(
            onDifficultyLevelSelected = {
                onDifficultyLevelSelected(it, true)
                showDialog = false
            },
            onDismissRequest = { showDialog = false }
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(100.dp))
                    .size(60.dp)
                    .clickable {
                        showDialog = true
                    }
                    .background(color = TopButtonsBackground),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "Зміна рівню важкості"
                )
            }
            Text(
                text = stringResource(id = R.string.app_name_ua),
                fontFamily = Inter,
                fontSize = 46.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(100.dp))
                    .size(60.dp)
                    .clickable { onHintClicked() }
                    .background(color = TopButtonsBackground),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.hint_icon),
                    contentDescription = "Підказка"
                )
            }
        }
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
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(emptyList(), "Test", {}, {}, {}, {}, { _: DifficultyLevel, _: Boolean -> })
}