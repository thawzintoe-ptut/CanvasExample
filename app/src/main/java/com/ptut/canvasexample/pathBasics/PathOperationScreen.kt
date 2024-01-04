package com.ptut.canvasexample.pathBasics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PathOperationScreen() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val squareWithoutOp = Path().apply {
            addRect(Rect(Offset(200f, 200f), Size(200f, 200f)))
        }
        val circle = Path().apply {
            addOval(
                Rect(
                    Offset(200f, 200f),
                    100f
                )
            )
        }
        val pathWithOp = Path().apply {
            op(
                squareWithoutOp,
                circle,
                PathOperation.Difference
            )
        }
        drawPath(
            path = squareWithoutOp,
            color = Color.Red,
            style = Stroke(
                width = 2.dp.toPx()
            )
        )
        drawPath(
            path = circle,
            color = Color.Blue,
            style = Stroke(
                width = 2.dp.toPx()
            )
        )
        drawPath(
            path = pathWithOp,
            color = Color.Green
        )
    }
}
