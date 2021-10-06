package com.example.mynoteappcourse.feature_note.domain.use_cases

import com.example.mynoteappcourse.feature_note.domain.model.Note
import com.example.mynoteappcourse.feature_note.domain.repository.NoteRepository
import com.example.mynoteappcourse.feature_note.domain.util.NoteOrder
import com.example.mynoteappcourse.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Title(OrderType.Ascending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { listNote ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Date -> listNote.sortedBy { it.timeStamp}
                        is NoteOrder.Title -> listNote.sortedBy { it.title }
                        is NoteOrder.Color -> listNote.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Date -> listNote.sortedByDescending { it.timeStamp}
                        is NoteOrder.Title -> listNote.sortedByDescending { it.title }
                        is NoteOrder.Color -> listNote.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}