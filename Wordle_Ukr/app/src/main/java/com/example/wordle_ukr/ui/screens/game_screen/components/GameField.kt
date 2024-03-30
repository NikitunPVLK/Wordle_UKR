package com.example.wordle_ukr.ui.screens.game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle_ukr.R
import com.example.wordle_ukr.ui.models.Letter
import com.example.wordle_ukr.ui.models.LetterState
import com.example.wordle_ukr.ui.theme.Inter

@Composable
fun GameField(
    words: List<List<Letter>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.height(385.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(words) { word ->
            WordRow(word = word)
        }
    }
}

@Composable
fun WordRow(
    word: List<Letter>
) {
    LazyRow(
        modifier = Modifier.width(320.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        items(word) { letter ->
            LetterContainer(letter)
        }
    }
}

@Composable
fun LetterContainer(
    letter: Letter,
    modifier: Modifier = Modifier
) {
    val color = when(letter.state) {
        LetterState.UNDEFINED -> MaterialTheme.colorScheme.primaryContainer
        LetterState.CORRECT -> MaterialTheme.colorScheme.secondary
        LetterState.DISPOSITION -> MaterialTheme.colorScheme.tertiary
        LetterState.WRONG -> MaterialTheme.colorScheme.error
    }
    Card(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.letter_container_size)),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.letter_container_radius)
        ),
        elevation = CardDefaults.cardElevation(
            dimensionResource(id = R.dimen.letter_container_elevation)
        ),
        colors = CardDefaults.cardColors(color),

        ) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = letter.char.uppercase(),
                fontFamily = Inter,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 38.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GameFieldPreview() {
//    GameField(
//        listOf(
//            listOf("Ж","Ж","Ж","Ж","Ж"),
//            listOf("Ж","Ж","Ж","Ж","Ж"),
//            listOf("Ж","Ж","Ж","Ж","Ж"),
//            listOf("Ж","Ж","Ж","Ж","Ж"),
//            listOf("Ж","Ж","Ж","Ж","Ж"),
//            listOf("Ж","Ж","Ж","Ж","Ж"),
//        )
//    )
//}


@Preview(showBackground = true)
@Composable
fun LetterContainerPreview() {
    LetterContainer(Letter("Ж", LetterState.CORRECT))
}