package fi.developer.kotlinmultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import fi.developer.kotlinmultiplatform.ui.screens.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    App()
}