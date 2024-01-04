package com.ptut.canvasexample.drawCubic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun PathCubic(
    radius: Dp = 15.dp
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        var shouldDrawQuad by remember {
            mutableStateOf(true)
        }

        var quadHandle1 by remember {
            mutableStateOf(Offset(constraints.maxWidth / 3f, constraints.maxHeight / 2f))
        }
        var isDraggingQuadHandle1 by remember {
            mutableStateOf(false)
        }
        var quadHandle2 by remember {
            mutableStateOf(Offset(2 * constraints.maxWidth / 3f, constraints.maxHeight / 2f))
        }
        var isDraggingQuadHandle2 by remember {
            mutableStateOf(false)
        }
        var quadControlPoint by remember {
            mutableStateOf(Offset(constraints.maxWidth / 2f, constraints.maxHeight / 4f))
        }
        var isDraggingQuadControlPoint by remember {
            mutableStateOf(false)
        }

        var cubicHandle1 by remember {
            mutableStateOf(Offset(constraints.maxWidth / 3f, constraints.maxHeight / 2f))
        }
        var isDraggingCubicHandle1 by remember {
            mutableStateOf(false)
        }
        var cubicHandle2 by remember {
            mutableStateOf(Offset(2 * constraints.maxWidth / 3f, constraints.maxHeight / 2f))
        }
        var isDraggingCubicHandle2 by remember {
            mutableStateOf(false)
        }
        var cubicControlPoint1 by remember {
            mutableStateOf(Offset(constraints.maxWidth / 3f, constraints.maxHeight / 4f))
        }
        var isDraggingCubicControlPoint1 by remember {
            mutableStateOf(false)
        }
        var cubicControlPoint2 by remember {
            mutableStateOf(Offset(2 * constraints.maxWidth / 3f, constraints.maxHeight / 4f))
        }
        var isDraggingCubicControlPoint2 by remember {
            mutableStateOf(false)
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectDragGestures(
                        onDragStart = { touchPoint ->
                            if (shouldDrawQuad) {
                                val distanceHandle1 = touchPoint.distance(quadHandle1)
                                val distanceHandle2 = touchPoint.distance(quadHandle2)
                                val distanceControlPoint = touchPoint.distance(quadControlPoint)
                                when {
                                    distanceHandle1 < radius.toPx() -> isDraggingQuadHandle1 = true
                                    distanceHandle2 < radius.toPx() -> isDraggingQuadHandle2 = true
                                    distanceControlPoint < radius.toPx() -> isDraggingQuadControlPoint = true
                                }
                            } else {
                                val distanceHandle1 = touchPoint.distance(cubicHandle1)
                                val distanceHandle2 = touchPoint.distance(cubicHandle2)
                                val distanceControlPoint1 = touchPoint.distance(cubicControlPoint1)
                                val distanceControlPoint2 = touchPoint.distance(cubicControlPoint2)
                                when {
                                    distanceHandle1 < radius.toPx() -> isDraggingCubicHandle1 = true
                                    distanceHandle2 < radius.toPx() -> isDraggingCubicHandle2 = true
                                    distanceControlPoint1 < radius.toPx() -> isDraggingCubicControlPoint1 = true
                                    distanceControlPoint2 < radius.toPx() -> isDraggingCubicControlPoint2 = true
                                }
                            }

                        },
                        onDragEnd = {
                            isDraggingQuadHandle1 = false
                            isDraggingQuadHandle2 = false
                            isDraggingQuadControlPoint = false

                            isDraggingCubicHandle1 = false
                            isDraggingCubicHandle2 = false
                            isDraggingCubicControlPoint1 = false
                            isDraggingCubicControlPoint2 = false
                        }
                    ) { change, _ ->
                        val touchPoint = change.position
                        when {
                            isDraggingQuadHandle1 -> quadHandle1 = touchPoint
                            isDraggingQuadHandle2 -> quadHandle2 = touchPoint
                            isDraggingQuadControlPoint -> quadControlPoint = touchPoint
                            isDraggingCubicHandle1 -> cubicHandle1 = touchPoint
                            isDraggingCubicHandle2 -> cubicHandle2 = touchPoint
                            isDraggingCubicControlPoint1 -> cubicControlPoint1 = touchPoint
                            isDraggingCubicControlPoint2 -> cubicControlPoint2 = touchPoint
                        }
                    }
                }
        ) {
            if (shouldDrawQuad) {
                listOf(quadHandle1, quadHandle2).forEach {
                    drawCircle(
                        color = Color.Red,
                        radius = radius.toPx(),
                        center = it
                    )
                }
                drawCircle(
                    color = Color.Blue,
                    radius = radius.toPx(),
                    center = quadControlPoint
                )
                val quadPath = Path().apply {
                    moveTo(quadHandle1.x, quadHandle1.y)
                    quadraticBezierTo(
                        quadControlPoint.x,
                        quadControlPoint.y,
                        quadHandle2.x,
                        quadHandle2.y
                    )
                }
                drawPath(
                    path = quadPath,
                    color = Color.Black,
                    style = Stroke(width = 2.dp.toPx())
                )
            } else {
                listOf(cubicHandle1, cubicHandle2).forEach {
                    drawCircle(
                        color = Color.Red,
                        radius = radius.toPx(),
                        center = it
                    )
                }
                listOf(cubicControlPoint1, cubicControlPoint2).forEach {
                    drawCircle(
                        color = Color.Blue,
                        radius = radius.toPx(),
                        center = it
                    )
                }
                val cubicPath = Path().apply {
                    moveTo(cubicHandle1.x, cubicHandle1.y)
                    cubicTo(
                        cubicControlPoint1.x,
                        cubicControlPoint1.y,
                        cubicControlPoint2.x,
                        cubicControlPoint2.y,
                        cubicHandle2.x,
                        cubicHandle2.y
                    )
                }
                drawPath(
                    path = cubicPath,
                    color = Color.Black,
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
        Button(
            onClick = { shouldDrawQuad = !shouldDrawQuad },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-100).dp)
        ) {
            Text(text = if (shouldDrawQuad) "Draw cubic" else "Draw quad")
        }
    }
}

private fun Offset.distance(to: Offset): Float {
    return sqrt((x - to.x).pow(2) + (y - to.y).pow(2))
}
