/*
 * Copyright 2024 Assertive UI (assertiveui.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.assertiveui.kit.lite.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assertiveui.kit.lite.theme.UITheme
import com.assertiveui.kit.lite.theme.utils.setupEdgeToEdge

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable Edge-To-Edge for Assertive UI Kit.
        setupEdgeToEdge()

        setContent {
            UITheme {

                val viewModel = viewModel<MainActivityViewModel>()
                val basePalette by viewModel.baseColorPalette.collectAsStateWithLifecycle()
                val accentPalette by viewModel.accentColorPalette.collectAsStateWithLifecycle()
                val surfacePalette by viewModel.surfaceColorPalette.collectAsStateWithLifecycle()
                val errorPalette by viewModel.errorColorPalette.collectAsStateWithLifecycle()

                PaletteDisplay(
                    basePalette = basePalette,
                    accentPalette = accentPalette,
                    surfacePalette = surfacePalette,
                    errorPalette = errorPalette,
                    onHueChanged = viewModel::onHueChanged
                )

            }
        }

    }

}

@Composable
fun PaletteDisplay(
    basePalette: List<Color>,
    accentPalette: List<Color>,
    surfacePalette: List<Color>,
    errorPalette: List<Color>,
    onHueChanged: (Int) -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val hue by infiniteTransition.animateValue(
        initialValue = 0,
        targetValue = 359,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    LaunchedEffect(
        key1 = hue,
        block = { onHueChanged(hue) }
    )

    val textColor by rememberUpdatedState(
        if (isSystemInDarkTheme()) {
            surfacePalette.getOrNull((surfacePalette.size - 4)) ?: Color.White
        } else {
            surfacePalette.getOrNull(3) ?: Color.Black
        }
    )

    val bgColor by rememberUpdatedState(
        if (!isSystemInDarkTheme()) {
            surfacePalette.getOrNull((surfacePalette.size - 4)) ?: Color.Transparent
        } else {
            surfacePalette.getOrNull(3) ?: Color.Transparent
        }
    )

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 16.dp),
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        columns = GridCells.Fixed(10)
    ) {

        item(
            span = { GridItemSpan(maxLineSpan) },
            content = {

                Heading(
                    text = "Base",
                    textColor = textColor
                )

            }
        )

        items(
            items = basePalette,
            itemContent = { color -> ColorBox(color = color) }
        )

        item(
            span = { GridItemSpan(maxLineSpan) },
            content = {

                Heading(
                    text = "Accent",
                    textColor = textColor
                )

            }
        )

        items(
            items = accentPalette,
            itemContent = { color -> ColorBox(color = color) }
        )

        item(
            span = { GridItemSpan(maxLineSpan) },
            content = {

                Heading(
                    text = "Surface",
                    textColor = textColor
                )

            }
        )

        items(
            items = surfacePalette,
            itemContent = { color -> ColorBox(color = color) }
        )

        item(
            span = { GridItemSpan(maxLineSpan) },
            content = {

                Heading(
                    text = "Error",
                    textColor = textColor
                )

            }
        )

        items(
            items = errorPalette,
            itemContent = { color -> ColorBox(color = color) }
        )

    }

}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    color: Color
) = Box(
    modifier = modifier
        .aspectRatio(1f)
        .fillMaxSize()
        .background(color)
)

@Composable
fun Heading(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color
) = Column(modifier = modifier) {

    Spacer(modifier = Modifier.height(32.dp))

    BasicText(
        text = "$text Color Tones",
        style = TextStyle.Default.copy(
            color = textColor
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

}