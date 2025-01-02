package com.cale.mccammon.kmp.trivia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cale.mccammon.kmp.trivia.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinMultiPlatformTrivia",
    ) {
        App()
    }
}