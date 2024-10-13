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

import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat

/**
 *
 * A function to enable fullscreen mode (Edge-To-Edge).
 * Used for displaying the UI content behind the SystemUI bars, required by Assertive UI Kit.
 *
 * Make sure to call this function inside the `onCreate()` function of a [ComponentActivity].
 *
 * ````
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
 * ````
 *
 */
fun ComponentActivity.setupEdgeToEdge() = WindowCompat
    .setDecorFitsSystemWindows(window, false)