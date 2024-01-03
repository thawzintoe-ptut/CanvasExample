package com.ptut.canvasexample.clockCanvas

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ptut.canvasexample.weightPicker.LineType
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    seconds: Float = 0f,
    minutes: Float = 0f,
    hours: Float = 0f,
    radius: Dp = 100.dp,
    style: ClockStyle = ClockStyle()
) {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    var angle by remember {
        mutableFloatStateOf(0f)
    }

    Canvas(modifier = Modifier.size(radius * 2f)) {
        center = this.center
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                radius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
            // Draw the clock scale
            for (minute in 0..59) {
                val angleInRad = (minute - 0 + angle) * PI.toFloat() / 30f
                val lineType = when {
                    minute % 5 == 0 -> LineType.FiveStep
                    else -> LineType.Normal
                }
                val lineLength = when (lineType) {
                    LineType.Normal -> style.normalLineLength.toPx()
                    else -> style.fiveStepLineLength.toPx()
                }
                val lineColor = when (lineType) {
                    LineType.Normal -> style.normalLineColor
                    else -> style.fiveStepLineColor
                }
                val lineStart = Offset(
                    x = radius.toPx() * cos(angleInRad) + center.x,
                    y = radius.toPx() * sin(angleInRad) + center.y
                )
                val lineEnd = Offset(
                    x = (radius.toPx() - lineLength) * cos(angleInRad) + center.x,
                    y = (radius.toPx() - lineLength) * sin(angleInRad) + center.y
                )
                drawLine(
                    color = lineColor,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = 1.dp.toPx()
                )
            }
            rotate(degrees = seconds * (360f / 60f)) {
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Red,
                    start = center,
                    end = Offset(
                        x = center.x,
                        y = 20.dp.toPx()
                    ),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(degrees = minutes * (360f / 60f)) {
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Black,
                    start = center,
                    end = Offset(
                        x = center.x,
                        y = 20.dp.toPx()
                    ),
                    strokeWidth = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(degrees = hours * (360f / 12f)) {
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Green,
                    start = center,
                    end = Offset(
                        x = center.x,
                        y = 35.dp.toPx()
                    ),
                    strokeWidth = 10.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}
