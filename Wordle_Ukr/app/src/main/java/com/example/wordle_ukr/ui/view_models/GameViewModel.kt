package com.example.wordle_ukr.ui.view_models

import androidx.lifecycle.ViewModel
import com.example.wordle_ukr.ui.models.Letter
import com.example.wordle_ukr.ui.models.LetterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(
    private val wordsList: List<String>
) : ViewModel() {

    private lateinit var hiddenWord: String

    private lateinit var _gameField: MutableStateFlow<MutableList<MutableList<Letter>>>
    val gameField
        get() = _gameField.asStateFlow()

    private var currentLine: Int = 0
    private var currentLetterPosition: Int = 0

    private var _helperText = MutableStateFlow("")
    val helperText
        get() = _helperText.asStateFlow()

    init {
        restartGame(true)
    }

    private fun restartGame(firstTime: Boolean) {
//        hiddenWord = "абоба"
        hiddenWord = wordsList.random()
        initialize(firstTime)
    }

    fun onKeyClicked(char: String) {
        if (currentLetterPosition <= 4) {

            _gameField.update {
                val newList = it.map { list -> list.toMutableList() }.toMutableList()
                newList[currentLine][currentLetterPosition] =
                    Letter(char.lowercase(), LetterState.UNDEFINED)
                newList
            }
            currentLetterPosition++
        }
    }

    fun onDeleteClicked() {
        if (currentLetterPosition > 0) {
            currentLetterPosition--
            _gameField.update {
                val newList = it.map { list -> list.toMutableList() }.toMutableList()
                newList[currentLine][currentLetterPosition] = Letter("", LetterState.UNDEFINED)
                newList
            }
        }
    }

    fun onSubmitClicked() {
        if (currentLetterPosition < 4) {
            return
        }
        currentLine++
        currentLetterPosition = 0
        val guessedWord = _gameField.value[currentLine - 1]
        var guessedWordAsString = ""
        for (letter in guessedWord) {
            guessedWordAsString += letter.char
        }
        if (hiddenWord == guessedWordAsString) {
            _helperText.value = "Перемога!"
            restartGame(false)
            return
        }
        if (currentLine > 5) {
            restartGame(false)
            _helperText.value = "Програв 8("
            return
        }
        if (!wordsList.contains(guessedWordAsString)) {
            _helperText.value = "Не є словом :/"
            currentLine--
            _gameField.update {
                val newList = it.map { list -> list.toMutableList() }.toMutableList()
                newList[currentLine] = mutableListOf(
                    Letter("", LetterState.UNDEFINED),
                    Letter("", LetterState.UNDEFINED),
                    Letter("", LetterState.UNDEFINED),
                    Letter("", LetterState.UNDEFINED),
                    Letter("", LetterState.UNDEFINED)
                )
                newList
            }
            return
        }
        val newLine = mutableListOf<Letter>()
        for (i in 0..4) {
            val state: LetterState =
                if (hiddenWord[i].toString() == guessedWord[i].char) {
                    LetterState.CORRECT
                } else if (hiddenWord.contains(guessedWord[i].char)) {
                    LetterState.DISPOSITION
                } else {
                    LetterState.WRONG
                }
            val newLetter = Letter(guessedWord[i].char, state)
            newLine.add(newLetter)
        }
        _gameField.update {
            val newList = it.map { list -> list.toMutableList() }.toMutableList()
            newList[currentLine - 1] = newLine
            newList
        }
    }

    fun onHintClicked() {

    }

    private fun initialize(firstTime: Boolean) {
        currentLine = 0
        currentLetterPosition = 0
        val emptyField = mutableListOf(
            mutableListOf(
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED)
            ),
            mutableListOf(
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED)
            ),
            mutableListOf(
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED)
            ),
            mutableListOf(
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED)
            ),
            mutableListOf(
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED)
            ),
            mutableListOf(
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED),
                Letter("", LetterState.UNDEFINED)
            )
        )
        if (firstTime) {
            _gameField = MutableStateFlow(
                emptyField
            )
        } else {
            _gameField.value = emptyField
        }
    }

}