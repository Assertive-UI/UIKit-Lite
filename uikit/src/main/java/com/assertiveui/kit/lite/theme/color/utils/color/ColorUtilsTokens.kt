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

/**
 * A collection of default token values used across [ColorUtils].
 */
internal sealed interface ColorUtilsTokens {

    /**
     * Default token values used for color tones generation.
     */
    data object ColorGeneration : ColorUtilsTokens {

        const val TONES_COUNT = 30

        const val TONES_LIGHTNESS_START = 0f
        const val TONES_LIGHTNESS_MID = 50f
        const val TONES_LIGHTNESS_END = 100f

        const val TONES_CHROMA_VIBRANT = 75f
        const val TONES_CHROMA_MUTED = 15f
        const val TONES_CHROMA_ZERO = 0f

        const val TONES_HUE_OFFSET = 30
        const val TONES_ALPHA = 1f

    }

}