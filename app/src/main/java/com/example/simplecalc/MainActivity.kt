package com.example.simplecalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplecalc.ui.theme.SimpleCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleCalcApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleCalcApp(modifier: Modifier = Modifier) {

    var number1 = remember { mutableStateOf("") }
    var number2 = remember { mutableStateOf("") }
    var result = remember { mutableStateOf("Результат: ") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            }
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Простий Калькулятор",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = number1.value,
            onValueChange = {
                number1.value = it
            },
            label = {
                Text("Перше число")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = number2.value,
            onValueChange = {
                number2.value = it
            },
            label = {
                Text("Друге число")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    focusManager.clearFocus()
                    val a = number1.value.toDoubleOrNull()
                    val b = number2.value.toDoubleOrNull()

                    if (a != null && b != null) {
                        result.value = "Результат: ${a + b}"
                    } else {
                        result.value = "Введіть два числа"
                    }
                }
            ) {
                Text("+", fontSize = 24.sp)
            }

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    focusManager.clearFocus()
                    val a = number1.value.toDoubleOrNull()
                    val b = number2.value.toDoubleOrNull()

                    if (a != null && b != null) {
                        result.value = "Результат: ${a - b}"
                    } else {
                        result.value = "Введіть два числа"
                    }
                }
            ) {
                Text("-", fontSize = 24.sp)
            }

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    focusManager.clearFocus()
                    val a = number1.value.toDoubleOrNull()
                    val b = number2.value.toDoubleOrNull()

                    if (a != null && b != null) {
                        result.value = "Результат: ${a * b}"
                    } else {
                        result.value = "Введіть два числа"
                    }
                }
            ) {
                Text("x", fontSize = 24.sp)
            }

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    focusManager.clearFocus()
                    val a = number1.value.toDoubleOrNull()
                    val b = number2.value.toDoubleOrNull()

                    if (a != null && b != null) {
                        if (b != 0.0) {
                            result.value = "Результат: ${a / b}"
                        } else {
                            result.value = "Помилка - ділення на нуль"
                        }
                    } else {
                        result.value = "Введіть два числа"
                    }
                }
            ) {
                Text("/", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = result.value,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCalcTheme {
        SimpleCalcApp()
    }
}