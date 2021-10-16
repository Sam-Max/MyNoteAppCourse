package com.example.mynoteappcourse.feature_note.presentation.add_edit_note

sealed class OneTimeEvents {
    object SavedNote: OneTimeEvents()
    class ShowSnackBar(message: String): OneTimeEvents()
}