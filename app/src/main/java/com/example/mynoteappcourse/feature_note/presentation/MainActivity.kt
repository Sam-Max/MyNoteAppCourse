package com.example.mynoteappcourse.feature_note.presentation

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynoteappcourse.feature_note.domain.model.Note
import com.example.mynoteappcourse.feature_note.presentation.notes.components.NoteItem
import com.example.mynoteappcourse.feature_note.presentation.notes.components.SelectionSection
import com.example.mynoteappcourse.ui.theme.MyNoteAppCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoteAppCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NoteItem(
                        note = Note(
                            title = "WandaVision",
                            content = "WandaVision it is a cool tv show form Marvel",
                            timeStamp = SystemClock.currentThreadTimeMillis(),
                            color = Color.BLUE
                        )
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyNoteAppCourseTheme {
        Greeting("Android")
    }
}