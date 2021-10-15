package com.example.mynoteappcourse.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynoteappcourse.feature_note.domain.model.Note
import com.example.mynoteappcourse.feature_note.domain.use_cases.NoteUseCases
import com.example.mynoteappcourse.feature_note.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel(
    private val notesUseCase: NoteUseCases
): ViewModel() {

    private val _state= mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note?= null

    fun onEvent(event: NotesEvent){
        when(event) {
            is NotesEvent.GetNotes ->{
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCase.deleteNoteUseCase(event.note)
                    recentlyDeletedNote= event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCase.insertNoteUseCase(recentlyDeletedNote ?: return@launch)
                }
            }
            is NotesEvent.ToggleOrderSection ->{
                _state.value= NotesState(
                    isOrderSectionCollapsed = !state.value.isOrderSectionCollapsed
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
       notesUseCase.getNotesUseCase(noteOrder)
           .onEach {
               _state.value= state.value.copy(
                   notes = it,
                   noteOrder = noteOrder
               )
           }
            .launchIn(viewModelScope)
    }

}