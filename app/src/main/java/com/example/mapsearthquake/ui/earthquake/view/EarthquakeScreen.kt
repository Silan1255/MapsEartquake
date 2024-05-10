package com.example.mapsearthquake.ui.earthquake.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mapsearthquake.R
import com.example.mapsearthquake.data.model.EarthquakeListResponse
import com.example.mapsearthquake.theme.Gray
import com.example.mapsearthquake.ui.earthquake.viewModel.EarthquakeViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun EarthquakeScreen(
    navController: NavHostController,
    viewModel: EarthquakeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val results = state?.result) {
            null -> LoadingView()
            else -> if (results.isNotEmpty()) {
                EarthquakeList(results, navController)
            } else {
                EmptyView()
            }
        }
    }
}

@Composable
fun EarthquakeList(
    earthquakeList: List<EarthquakeListResponse.Result>,
    navController: NavHostController
) {
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
        items(earthquakeList) { earthquake ->
            ItemView(earthquake = earthquake, navController)
        }

    }
}

@Composable
fun ItemView(earthquake: EarthquakeListResponse.Result, navController: NavHostController) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                earthquake.geojson?.coordinates?.let { coordinates ->
                    if (coordinates.size >= 2) {
                        val latitude = coordinates[1]
                        val longitude = coordinates[0]
                        navController.navigate("earthquake_map_screen/$latitude/$longitude")
                    }
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Gray)
        ) {
            Text(
                text = earthquake.mag.toString(),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
                color = Color.White
            )
        }
        Column(
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(
                modifier = Modifier.offset(y = 8.dp),
                text = earthquake.title.toString(),
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "Your Image",
                        modifier = Modifier.padding(all = 2.dp)
                    )
                    Text(
                        text = formatDate(earthquake.date.toString()),
                        fontSize = 10.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Divider(
                        color = Color.Black,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(4.dp)
                            .height(1.dp)
                            .align(Alignment.CenterVertically)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Your Image",
                        modifier = Modifier.padding(all = 2.dp)
                    )
                    Text(
                        text = formatTime(earthquake.date.toString()),
                        fontSize = 10.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Your Image",
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }

        }
    }
}

@Composable
fun formatDate(dateString: String): String {
    val parser = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return try {
        val date = parser.parse(dateString)
        formatter.format(date!!)
    } catch (e: ParseException) {
        dateString
    }
}

@Composable
fun formatTime(dateString: String): String {
    val parser = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return try {
        val date = parser.parse(dateString)
        formatter.format(date!!)
    } catch (e: ParseException) {
        dateString
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

        val navController = rememberNavController()
        ItemView(sampleEarthquake, navController)
    }
}
