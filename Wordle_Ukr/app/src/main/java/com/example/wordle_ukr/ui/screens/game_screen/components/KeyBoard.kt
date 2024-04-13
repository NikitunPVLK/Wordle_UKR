package com.example.wordle_ukr.ui.screens.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle_ukr.R
import com.example.wordle_ukr.ui.theme.DeleteKeyColor
import com.example.wordle_ukr.ui.theme.Inter
import com.example.wordle_ukr.ui.theme.Green

@Composable
fun Keyboard(
    alphabet: String,
    onKeyClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onSubmitClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(13) {
                Key(alphabet[it].toString(), onKeyClick)
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(11) {
                Key(alphabet[13 + it].toString(), onKeyClick)
            }
            item {
                DeleteKey(onDeleteClick)
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(10) {
                Key(alphabet[24 + it].toString(), onKeyClick)
            }
            item {
                SubmitKey(onSubmitClick)
            }
        }
    }
}

@Composable
fun Key(
    char: String,
    onKeyClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.key_width))
            .height(dimensionResource(id = R.dimen.key_height)),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.key_radius)),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.key_elevation)),
        onClick = {
            onKeyClick(char)
        }
    ) {
        Text(
            text = char.uppercase(),
            fontFamily = Inter,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DeleteKey(
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.delete_key_width))
            .height(dimensionResource(id = R.dimen.delete_key_height)),
        colors = ButtonDefaults.buttonColors(DeleteKeyColor),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.delete_key_radius)),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.key_elevation)),
        onClick = onDeleteClick
    ) {
        Image(
            painter = painterResource(id = R.drawable.backspace),
            contentDescription = stringResource(id = R.string.backspace_description)
        )
    }
}

@Composable
fun SubmitKey(
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.submit_key_width))
            .height(dimensionResource(id = R.dimen.submit_key_height)),
        colors = ButtonDefaults.buttonColors(Green),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.submit_key_radius)),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.key_elevation)),
        onClick = onSubmitClick
    ) {
        Text(
            text = stringResource(id = R.string.submit_key_text),
            color = Color.White,
            fontFamily = Inter,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KeyPreview() {
    Key("Ж", {})
}

@Preview(showBackground = true)
@Composable
fun DeleteKeyPreview() {
    DeleteKey({})
}

@Preview(showBackground = true)
@Composable
fun SubmitKeyPreview() {
    SubmitKey({})
}

@Preview(showBackground = true)
@Composable
fun KeyboardPreview() {
    Keyboard(
        stringResource(id = R.string.alphabet),
        {},
        {},
        {}
    )
}