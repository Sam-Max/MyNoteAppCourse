package com.example.mynoteappcourse.feature_note.presentation

import com.example.mynoteappcourse.feature_note.domain.model.Note
import com.example.mynoteappcourse.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    class GetNotes(val noteOrder: NoteOrder): NotesEvent()
    class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection: NotesEvent()
}