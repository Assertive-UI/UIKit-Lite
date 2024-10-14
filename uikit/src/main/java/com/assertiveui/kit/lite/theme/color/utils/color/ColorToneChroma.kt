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

import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_CHROMA_MUTED
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_CHROMA_VIBRANT
import com.assertiveui.kit.lite.theme.color.utils.color.ColorUtilsTokens.ColorGeneration.TONES_CHROMA_ZERO

/**
 * A collection of constants used for color tone chroma evaluations.
 */
enum class ColorToneChroma(val value: Float) {

    /**
     * A constant that represents a saturated chroma.
     */
    VIBRANT(value = TONES_CHROMA_VIBRANT),

    /**
     * A constant that represents a desaturated chroma.
     */
    MUTED(value = TONES_CHROMA_MUTED),

    /**
     * A constant that represents a non-saturated chroma.
     */
    ZERO(value = TONES_CHROMA_ZERO);

}