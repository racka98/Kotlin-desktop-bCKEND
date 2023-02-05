package com.myapplication.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = ""

@Preview
@Composable
fun AppPreview() {
    App(ContextProvider())
}