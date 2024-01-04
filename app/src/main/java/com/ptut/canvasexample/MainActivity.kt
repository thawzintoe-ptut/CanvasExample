package com.ptut.canvasexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ptut.canvasexample.drawCubic.PathCubic
import com.ptut.canvasexample.weightPicker.ScaleStyle
import com.ptut.canvasexample.weightPicker.WeightScale
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PathCubic()
        }
    }
}





@Composable
fun MyCanvas() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp),
        onDraw = {
            drawRect(
                color = Color.Black,
                size = size
            )
            drawRect(
                color = Color.Red,
                topLeft = Offset(100f, 100f),
                size = Size(100f, 100f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Red, Color.Yellow),
                    center = center,
                    radius = 100f
                ),
                radius = 100f
            )
            drawArc(
                color = Color.Green,
                startAngle = 0f,
                sweepAngle = 270f,
                useCenter = true,
                topLeft = Offset(100f, 500f),
                size = Size(200f, 200f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
            drawOval(
                color = Color.Magenta,
                topLeft = Offset(500f, 100f),
                size = Size(200f, 300f)
            )
            drawLine(
                color = Color.Cyan,
                start = Offset(300f, 700f),
                end = Offset(700f, 700f),
                strokeWidth = 5.dp.toPx()
            )
        }
    )
}
