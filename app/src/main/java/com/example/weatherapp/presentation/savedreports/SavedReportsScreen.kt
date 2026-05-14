package com.example.weatherapp.presentation.savedreports



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.presentation.savedreports.components.EmptyReportsView
import com.example.weatherapp.presentation.savedreports.components.ReportCard


@Composable
fun SavedReportsScreen(

    viewModel: SavedReportsViewModel =
        hiltViewModel()

) {

    val reports by viewModel.reports
        .collectAsState()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(12.dp)

    ) {

        Text(

            text = "Saved Reports",

            style =
                MaterialTheme.typography.headlineMedium
        )

        if (reports.isEmpty()) {

            EmptyReportsView()

        } else {

            LazyColumn(

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {

                items(reports) { report ->

                    ReportCard(
                        report = report
                    )
                }
            }
        }
    }
}