package com.example.mynoteappcourse.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynoteappcourse.feature_note.domain.model.Note
import com.example.mynoteappcourse.feature_note.domain.use_cases.GetNoteUseCase
import com.example.mynoteappcourse.feature_note.domain.use_cases.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val insertNote: InsertNoteUseCase
): ViewModel()  {

    private val _noteTitle= mutableStateOf(NoteTextFieldState(
        hint = "Enter Title"
    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteBody= mutableStateOf(NoteTextFieldState(
        hint = "Enter Some Text"
    ))
    val noteBody: State<NoteTextFieldState> = _noteBody

    private val _noteColor= mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    val eventChannel= Channel<OneTimeEvents>()

    private val currentNoteId: Int?= null

    fun onEvent(event: AddEditNoteEvents){
        when (event){
            is AddEditNoteEvents.ChangeColor -> {
                _noteColor.value= event.color
            }
            is AddEditNoteEvents.ChangeContentFocus -> TODO()
            is AddEditNoteEvents.ChangeTitleFocus -> TODO()
            is AddEditNoteEvents.EnteredContent -> {
                _noteBody.value= NoteTextFieldState(
                    text = event.value
                )
            }
            is AddEditNoteEvents.EnteredTitle -> {
                _noteTitle.value= NoteTextFieldState(
                    text = event.value
                )
            }
            is AddEditNoteEvents.SaveNote -> {
                viewModelScope.launch {
                    try {
                        insertNote(Note(
                            title = noteTitle.value.text,
                            content= noteBody.value.text,
                            color= noteColor.value.toString(),
                            timeStamp = System.currentTimeMillis(),
                            id = currentNoteId
                        ))
                        eventChannel.send(OneTimeEvents.SavedNote)
                    } catch (e: Exception) {
                        eventChannel.send(OneTimeEvents.ShowSnackBar(e.message ?: "Could not save note"))
                    }
                }
            }
        }
    }

}