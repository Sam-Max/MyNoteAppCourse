package com.example.mynoteappcourse.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynoteappcourse.ui.theme.*

@Entity
data class Note (
    val title: String,
    val content: String,
    val color: String,
    val timeStamp: Long,
    @PrimaryKey val id: Int?= null
    )
{
    companion object{
        val noteColors= listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }

}