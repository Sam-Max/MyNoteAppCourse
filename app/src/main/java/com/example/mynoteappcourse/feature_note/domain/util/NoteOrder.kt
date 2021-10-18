package com.example.mynoteappcourse.feature_note.domain.util

sealed class NoteOrder (val orderType: OrderType) {
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)

    //copy function for not to refactor like data classes the sealed classes
    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}