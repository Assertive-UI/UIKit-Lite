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

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assertiveui.kit.lite.theme.color.utils.color.ColorToneChroma
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _baseColorPalette = MutableStateFlow<List<Color>>(emptyList())
    val baseColorPalette = _baseColorPalette.asStateFlow()

    private val _accentColorPalette = MutableStateFlow<List<Color>>(emptyList())
    val accentColorPalette = _accentColorPalette.asStateFlow()

    private val _surfaceColorPalette = MutableStateFlow<List<Color>>(emptyList())
    val surfaceColorPalette = _surfaceColorPalette.asStateFlow()

    private val _errorColorPalette = MutableStateFlow<List<Color>>(emptyList())
    val errorColorPalette = _errorColorPalette.asStateFlow()

    init {
        initialize()
    }

    fun onHueChanged(hue: Int) {
        viewModelScope.launch {

            launch {
                val newBaseColorPalette = ColorUtils.generateColorTones(hue = hue)
                _baseColorPalette.update { newBaseColorPalette }
            }

            launch {
                val newAccentColorPalette = ColorUtils.generateColorTones(hue = hue - 70)
                _accentColorPalette.update { newAccentColorPalette }
            }

            launch {

                val newSurfaceColorPalette = ColorUtils.generateColorTones(
                    hue = hue,
                    chroma = ColorToneChroma.MUTED
                )

                _surfaceColorPalette.update { newSurfaceColorPalette }

            }

        }
    }

    private fun initialize() {
        viewModelScope.launch {
            val newErrorColorPalette = ColorUtils.generateColorTones(hue = 0)
            _errorColorPalette.update { newErrorColorPalette }
        }
    }

}