package com.example.mynoteappcourse.feature_note.presentation.add_edit_note

import com.example.mynoteappcourse.feature_note.domain.use_cases.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase
) {



}