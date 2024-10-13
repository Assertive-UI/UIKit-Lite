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

package com.assertiveui.kit.lite.theme

import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.assertiveui.kit.lite.theme.utils.applyBasicAssertiveUIKitStyle
import com.stoyanvuchev.systemuibarstweaker.ProvideSystemUIBarsTweaker
import com.stoyanvuchev.systemuibarstweaker.rememberSystemUIBarsTweaker

/**
 *
 * A [UITheme] is the foundation of Assertive UI Kit theme.
 * It provides essential configuration to Assertive UI Kit Components and much more.
 * Therefore, it's considered as a Top-level composable function.
 *
 * Make sure to call this composable function inside `setContent {}`in the `onCreate()`
 * function of a [ComponentActivity], and wrap the UI Content inside.
 *
 * ```
 * class MainActivity : ComponentActivity() {
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *
 *         // Enable Edge-To-Edge for Assertive UI Kit.
 *         setupEdgeToEdge()
 *
 *         setContent {
 *             UITheme {
 *                  // UI Content ...
 *             }
 *         }
 *
 *     }
 *
 * }
 * ```
 *
 * @param darkTheme Whether to apply dark theme or not.
 * @param content The UI Content tree.
 *
 */
@Composable
fun UITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val tweaker = rememberSystemUIBarsTweaker()

    DisposableEffect(darkTheme, tweaker) {
        tweaker.applyBasicAssertiveUIKitStyle(darkTheme = darkTheme)
        onDispose {}
    }

    ProvideSystemUIBarsTweaker(
        systemUIBarsTweaker = tweaker,
        content = content
    )

}