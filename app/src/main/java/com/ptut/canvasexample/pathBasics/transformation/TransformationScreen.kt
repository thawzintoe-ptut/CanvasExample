package com.ptut.canvasexample.pathBasics.transformation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun TransformationScreen() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(
            degrees = 45f,
            pivot = Offset(200f, 200f)
        ) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(100f, 100f),
                size = Size(200f, 200f)
            )
        }

        translate(
            left = 300f,
            top = 300f
        ) {
            rotate(
                degrees = 45f,
                pivot = Offset(200f, 200f)
            ) {
                drawRect(
                    color = Color.Green,
                    topLeft = Offset(500f, 500f),
                    size = Size(200f, 200f)
                )
            }
            drawRect(
                color = Color.Blue,
                topLeft = Offset(500f, 500f),
                size = Size(200f, 200f)
            )
        }

        scale(
            scaleX = 0.5f,
            scaleY = 0.5f,
            pivot = Offset(1500f, 500f)
        ) {
            drawRect(
                color = Color.Black,
                topLeft = Offset(100f, 100f),
                size = Size(200f, 200f)
            )
        }

        val circle = Path().apply {
            addOval(
                Rect(
                    Offset(400f, 400f),
                    300f
                )
            )
        }
        drawPath(
            path = circle,
            color = Color.Black,
            style = Stroke(
                width = 5f,
                cap = StrokeCap.Round
            )
        )

        clipPath(
            path = circle
        ) {
            drawRect(
                color = Color.Blue,
                topLeft = Offset(400f, 400f),
                size = Size(400f, 400f)
            )
        }
    }
}
