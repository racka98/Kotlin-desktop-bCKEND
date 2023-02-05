import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.myapplication.common.App
import com.myapplication.common.ContextProvider

fun main() = application {
    val contextProvider = remember { ContextProvider() }

    Window(onCloseRequest = ::exitApplication) {
        App(contextProvider)
    }
}