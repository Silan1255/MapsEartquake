package com.example.mapsearthquake.ui.earthquake.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mapsearthquake.R
import com.example.mapsearthquake.data.model.EarthquakeListResponse
import com.example.mapsearthquake.ui.earthquake.viewModel.EarthquakeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun EarthquakeScreen(navController: NavHostController, viewModel: EarthquakeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val results = state?.result) {
            null -> LoadingView()  // Loading state
            else -> if (results.isNotEmpty()) {
                EarthquakeList(results)
            } else {
                EmptyView()  // Handle empty state
            }
        }
    }
}
@Composable
fun EarthquakeList(earthquakeList: List<EarthquakeListResponse.Result>) {
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
        items(earthquakeList) { earthquake ->
            ItemView(earthquake = earthquake)
        }
    }
}
@Composable
fun ItemView(earthquake: EarthquakeListResponse.Result) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),  // Consider using an appropriate image
            contentDescription = "Earthquake location icon",  // Adding content description for accessibility
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = "Title: ${earthquake.title}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Date: ${earthquake.date?.let { formatDate(it) }}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// Simple date formatter (you may need to adjust according to the date format you receive)
@Composable
fun formatDate(dateString: String): String {
    // Assuming the date format is yyyy.MM.dd HH:mm:ss
    val parser = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    return try {
        val date = parser.parse(dateString)
        formatter.format(date)
    } catch (e: ParseException) {
        dateString  // Return original string if parsing fails
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("No earthquakes to display", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemView() {
    MaterialTheme {
        val sampleEarthquake = EarthquakeListResponse.Result(
            id = "1",
            title = "M 5.1 - Near the coast of Central Chile",
            date = "2024.04.16 08:30:00"
        )

        ItemView(sampleEarthquake)
    }
}
