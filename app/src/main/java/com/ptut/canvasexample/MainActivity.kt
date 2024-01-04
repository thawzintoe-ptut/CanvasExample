package com.ptut.canvasexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ptut.canvasexample.pathBasics.PathArrowScreen
import com.ptut.canvasexample.pathBasics.PathEffectsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PathEffectsScreen()
        }
    }
}
