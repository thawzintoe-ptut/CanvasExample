package com.ptut.canvasexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.ptut.canvasexample.gender_picker.GenderPickerScreen
import com.ptut.canvasexample.pathBasics.PathEffectsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenderPickerScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
