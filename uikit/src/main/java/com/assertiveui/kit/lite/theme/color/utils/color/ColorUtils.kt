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

package com.assertiveui.kit.lite.theme.color.utils.color

import androidx.compose.ui.graphics.Color
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtils.generateColorTones
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_ALPHA
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_HUE_OFFSET
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_LIGHTNESS_END
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_LIGHTNESS_MID
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_LIGHTNESS_START
import com.github.ajalt.colormath.extensions.android.composecolor.toComposeColor
import com.github.ajalt.colormath.model.LCHab
import com.github.ajalt.colormath.model.Oklab
import com.github.ajalt.colormath.transform.InterpolationMethods
import com.github.ajalt.colormath.transform.interpolator
import com.github.ajalt.colormath.transform.sequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * A collection of crafted Utilities,
 * ranging from generating color tones, to more advanced color science.
 *
 * @property generateColorTones A function used for generating uniform color shades.
 *
 */
object ColorUtils {

    /**
     *
     * A function used to generate uniform color tones derived from a single hue value
     * by using an [Oklab] color space interpolation from the [com.github.ajalt.colormath]
     * library and producing variety of color tones.
     *
     * The interpolator use Monotonic spline
     * interpolation that interpolates smoothly between each pair of input points.
     *
     * Make sure to call this function in a thread-safe manner as it's performing
     * heavy calculations under-the-hood.
     *
     * @param hue The hue value ranging from 0 to 359.
     * @param tones The number of generated tones.
     * @param chroma Whether to generate a vibrant, muted tones or tones with zero chroma.
     *
     */
    suspend fun generateColorTones(
        hue: Int,
        tones: Int = ColorUtilsTokens.ColorGeneration.TONES_COUNT,
        chroma: ColorToneChroma = ColorToneChroma.VIBRANT
    ): List<Color> {
        return withContext(Dispatchers.Default) {

            val interpolator = Oklab.interpolator {

                method = InterpolationMethods.monotoneSpline()

                stop(
                    color = LCHab(
                        l = TONES_LIGHTNESS_START,
                        c = ColorToneChroma.ZERO.value,
                        h = hue + TONES_HUE_OFFSET,
                        alpha = TONES_ALPHA
                    )
                )

                stop(
                    color = LCHab(
                        l = TONES_LIGHTNESS_MID,
                        c = chroma.value,
                        h = hue + TONES_HUE_OFFSET,
                        alpha = TONES_ALPHA
                    )
                )

                stop(
                    color = LCHab(
                        l = TONES_LIGHTNESS_END,
                        c = ColorToneChroma.ZERO.value,
                        h = hue + TONES_HUE_OFFSET,
                        alpha = TONES_ALPHA
                    )
                )

            }

            return@withContext interpolator
                .sequence(tones)
                .distinct()
                .toList()
                .map { it.toComposeColor() }

        }
    }

}
