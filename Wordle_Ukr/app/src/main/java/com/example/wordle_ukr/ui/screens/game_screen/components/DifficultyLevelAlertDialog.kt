package com.example.wordle_ukr.ui.screens.game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle_ukr.ui.models.DifficultyLevel
import com.example.wordle_ukr.ui.theme.EasyLevel
import com.example.wordle_ukr.ui.theme.HardLevel
import com.example.wordle_ukr.ui.theme.Inter
import com.example.wordle_ukr.ui.theme.MixedLevel
import com.example.wordle_ukr.ui.theme.NormalLevel
import com.example.wordle_ukr.ui.theme.TextColor
import com.example.wordle_ukr.ui.theme.WordleUkrTheme

@Composable
fun DifficultyLevelAlertDialog(
    modifier: Modifier = Modifier,
    onDifficultyLevelSelected: (DifficultyLevel) -> Unit,
) {
    AlertDialog(
        modifier = modifier
            .width(300.dp)
            .height(420.dp),
        onDismissRequest = {},
        confirmButton = {},
        text = {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Оберіть рівень складності",
                    fontFamily = Inter,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    lineHeight = 28.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Button(
                    modifier = modifier.width(200.dp),
                    onClick = { onDifficultyLevelSelected(DifficultyLevel.EASY) },
                    colors = ButtonDefaults.buttonColors(containerColor = EasyLevel)
                ) {
                    Text(text = "Легкий",
                        fontFamily = Inter,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        lineHeight = 28.sp,
                        color = TextColor)
                }
                Button(
                    modifier = modifier.width(200.dp),
                    onClick = { onDifficultyLevelSelected(DifficultyLevel.NORMAL) },
                    colors = ButtonDefaults.buttonColors(containerColor = NormalLevel)
                ) {
                    Text(text = "Середній",
                        fontFamily = Inter,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        lineHeight = 28.sp,
                        color = TextColor)
                }
                Button(
                    modifier = modifier.width(200.dp),
                    onClick = { onDifficultyLevelSelected(DifficultyLevel.HARD) },
                    colors = ButtonDefaults.buttonColors(containerColor = HardLevel)
                ) {
                    Text(text = "Важкий",
                        fontFamily = Inter,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        lineHeight = 28.sp,
                        color = TextColor)
                }
                Button(
                    modifier = modifier.width(200.dp),
                    onClick = { onDifficultyLevelSelected(DifficultyLevel.MIXED) },
                    colors = ButtonDefaults.buttonColors(containerColor = MixedLevel)
                ) {
                    Text(text = "Всі слова",
                        fontFamily = Inter,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        lineHeight = 28.sp,
                        color = TextColor)
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun DifficultyLevelAlertDialogPreviewLight(
) {
    WordleUkrTheme(darkTheme = false) {
        DifficultyLevelAlertDialog(
            onDifficultyLevelSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DifficultyLevelAlertDialogPreviewDark(
) {
    WordleUkrTheme(darkTheme = true) {
        DifficultyLevelAlertDialog(
            onDifficultyLevelSelected = {}
        )
    }
}