package com.example.mynoteappcourse.feature_note.domain.use_cases

import com.example.mynoteappcourse.feature_note.domain.model.Note
import com.example.mynoteappcourse.feature_note.domain.repository.NoteRepository

class InsertNoteUseCase(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
            noteRepository.insertNote(note)
    }
}