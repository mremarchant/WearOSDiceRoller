package com.example.diceroller.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.diceroller.presentation.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    DiceRollerTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            DiceRollerScreen()
        }
    }
}

@Composable
fun DiceRollerScreen() {
    // State to store the last rolled result with button name
    var rolledResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f)) // Add space above the buttons

        // Buttons for dice
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First row with 3 buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DiceButton("d4") { rolledResult = "d4: ${(1..4).random()}" }
                DiceButton("d6") { rolledResult = "d6: ${(1..6).random()}" }
                DiceButton("d8") { rolledResult = "d8: ${(1..8).random()}" }
            }

            Spacer(modifier = Modifier.height(8.dp)) // Add padding between rows

            // Second row with 4 buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DiceButton("d10") { rolledResult = "d10: ${(1..10).random()}" }
                DiceButton("d12") { rolledResult = "d12: ${(1..12).random()}" }
                DiceButton("d20") { rolledResult = "d20: ${(1..20).random()}" }
                DiceButton("d100") { rolledResult = "d100: ${(1..100).random()}" }
            }
        }

        Spacer(modifier = Modifier.weight(0.4f)) // Add slightly less space below the buttons

        // Display the rolled result slightly higher and larger
        Text(
            text = rolledResult,
            style = MaterialTheme.typography.title1.copy(fontSize = 24.sp), // Increased font size
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 12.dp), // Raised text slightly higher
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun DiceButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(40.dp), // Reduced button size
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
    ) {
        Text(text = label, textAlign = TextAlign.Center, style = MaterialTheme.typography.body2)
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}
