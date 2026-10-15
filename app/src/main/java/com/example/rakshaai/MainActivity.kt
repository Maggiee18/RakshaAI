package com.example.rakshaai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rakshaai.ui.RakshaApp
import com.example.rakshaai.ui.theme.RakshaAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RakshaAITheme {
                RakshaApp()
            }
        }
    }
}
