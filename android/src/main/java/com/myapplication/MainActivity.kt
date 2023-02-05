package com.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.common.App
import com.myapplication.common.ContextProvider

class MainActivity : AppCompatActivity() {
    private val contextProvider = ContextProvider(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(contextProvider)
        }
    }
}