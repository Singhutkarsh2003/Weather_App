package com.example.weatherapp.presentation.report.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotesTextField(
    notes: String,
    onNotesChange: (String) -> Unit,
    modifier: Modifier = Modifier
){

    OutlinedTextField(
        value = notes,
        onValueChange = onNotesChange,
        modifier = modifier,
        label = {
            Text(text = "Notes")
        }
    )
}