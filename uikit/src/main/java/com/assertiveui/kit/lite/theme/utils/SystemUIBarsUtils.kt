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

package com.assertiveui.kit.lite.theme.utils

import android.os.Build
import androidx.compose.ui.graphics.Color
import com.stoyanvuchev.systemuibarstweaker.ScrimStyle
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsTweaker

/**
 *
 * A function to apply the basic Assertive UI Kit style to the System UI Bars.
 *
 * ```
 * val tweaker = rememberSystemUIBarsTweaker()
 * val darkTheme = isSystemInDarkTheme()
 *
 * DisposableEffect(darkTheme, tweaker) {
 *     tweaker.applyBasicAssertiveUIKitStyle(darkTheme)
 *     onDispose {}
 * }
 *
 * ProvideSystemUIBarsTweaker(systemUIBarsTweaker = tweaker) {
 *     // UI Content ...
 * }
 * ```
 *
 * @param darkTheme Whether to apply dark or light theme configuration.
 * @param lightThemeScrimColor The scrim color for light theme.
 * @param darkThemeScrimColor The scrim color for dark theme.
 *
 */
fun SystemUIBarsTweaker.applyBasicAssertiveUIKitStyle(
    darkTheme: Boolean,
    lightThemeScrimColor: Color = Color(0xFFFAFAFA),
    darkThemeScrimColor: Color = Color(0xFF0D0D0D),
) = tweakSystemBarsStyle(
    statusBarStyle = statusBarStyle.copy(darkIcons = !darkTheme),
    navigationBarStyle = navigationBarStyle.copy(
        darkIcons = !darkTheme,
        scrimStyle = if (!isGestureNavigationEnabled) {
            ScrimStyle.Custom(
                lightThemeColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    lightThemeScrimColor else darkThemeScrimColor,
                darkThemeColor = Color(0xFF0D0D0D),
            )
        } else ScrimStyle.None
    )
)