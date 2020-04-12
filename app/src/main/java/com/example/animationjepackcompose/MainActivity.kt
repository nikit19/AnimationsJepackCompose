package com.example.animationjepackcompose

import android.os.Bundle
import androidx.animation.FastOutLinearInEasing
import androidx.animation.FloatPropKey
import androidx.animation.Infinite
import androidx.animation.transitionDefinition
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.Transition
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.ContentGravity
import androidx.ui.geometry.Rect
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.layout.preferredSize
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Tween()
            }
        }
    }
}

private val angle = FloatPropKey()
private val transDef = transitionDefinition {
    state("start") {
        this[angle] = 0f
    }
    state("end") {
        this[angle] = 360f
    }
    transition("start" to "end") {
        angle using repeatable {
            animation = tween {
                duration = 3000
                easing = FastOutLinearInEasing
            }
            iterations = Infinite
        }
    }
}

@Composable
fun Tween() {
    Box(gravity = ContentGravity.Center, children = {
        Transition(
                definition = transDef,
                initState = "start",
                toState = "end"
        ) { state ->
            Canvas(modifier = Modifier.preferredSize(200.dp)) {
                save()
                val midX = size.width.value / 2
                val midY = size.height.value / 2
                drawArc(Rect(midX - 150, midY - 150, midX + 150, midY + 150), 0f, state[angle], true, Paint().apply {
                    color = Color(0, 138, 255)
                })
                drawArc(Rect(midX - 100, midY - 100, midX + 100, midY + 100), 0f, state[angle], true, Paint().apply {
                    color = Color(255, 13, 128)
                })
                drawArc(Rect(midX - 50, midY - 50, midX + 50, midY + 50), 0f, state[angle], true, Paint().apply {
                    color = Color(255, 255, 255)
                })
                restore()
            }
        }
    })
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Tween()
    }
}
