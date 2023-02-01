import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import java.io.File
import javax.swing.JFileChooser

fun selectFile(pathState: MutableState<String?>) {
    JFileChooser().apply {
        if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            pathState.value = selectedFile.path
        }
    }
}
@Composable
fun App() {
    MaterialTheme {
        Column {
        //Full Name Text Area **************************************************************************************
        var text by remember { mutableStateOf("") }
        Column(Modifier.padding(20.dp)){
            Text("Full Name", textAlign = TextAlign.Center)
            OutlinedTextField(
                value=text,
                onValueChange = { text= it},
                label = { Text("Full Name")}
            )
        }

        //Password Text Area ***********************************************************************************
        var password by remember { mutableStateOf("") }
        Text("Enter Your Password", textAlign = TextAlign.Center)
            Column(Modifier.padding(20.dp)) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password")},
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
        //Identification dropdown menu Text Area ***************************************************************
            var mExpanded by remember { mutableStateOf(false) }

            val icon = if (mExpanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            var mTextFieldSize by remember { mutableStateOf(" ")}
//            val listItems = arrayOf("Aadhaar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
            var mSelectedText by remember { mutableStateOf("") }
            Column(Modifier.padding(20.dp)) {
                Text("Select Valid Identification ", textAlign = TextAlign.Center)
                OutlinedTextField(
                    value = mSelectedText,
                    onValueChange = { mSelectedText = it },
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates -> mTextFieldSize = coordinates.size.toSize().toString() },
                    label = {Text("Label")},
                    trailingIcon = {
                        Icon(icon,"contentDescription", Modifier.clickable { mExpanded = !mExpanded })
                    }
                )

            }
//            ExposedDropdownMenu(Context)

            //Choose File ******************************************************************************************
            val sourcePath = remember { mutableStateOf<String?>(null) }
//            var isFileChooserOpen by remember { mutableStateOf(false) }
//
//            if (isFileChooserOpen) {
//                FileDialog(
//
//                    onCloseRequest = {
//                        isFileChooserOpen = false
//                        println("Result $it")
//                    }
//                )
//            }
            Column(
                modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    selectFile(sourcePath)
                    println(sourcePath.value)

//                    isFileChooserOpen = true
                }) {
                    Text("Choose File")
                }
            }
            //Submit Button ******************************************************************************************
                Column(
                    modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {
                        val folder = "C:\\Intern"
                        val fileName= text
                        val f = File(folder, fileName)
                        f.mkdir()
                        //Creating New Folder inside Name for identification
                        val folder1 = folder+"\\"+fileName
                        val fileName1= mSelectedText
                        val f1 = File(folder1, fileName1)
                        f1.mkdir()
                        val destinePath:String =folder1+"\\"+fileName1
                        val destination:String = destinePath

                        val source:String = sourcePath.value as String
                        println(source)
                        println(destination)
//
//
//                        val sourcePath1 = Paths.get(source)
//                        val targetPath1 = Paths.get(destination)

                    }) {
                        Text("Submit")
                    }
                }
            }


        }

}

//@Composable
//private fun FileDialog(
//    parent: Frame? = null,
//    onCloseRequest: (result: String?) -> Unit
//) = AwtWindow(
//    create = {
//        object : FileDialog(parent, "Choose a file", LOAD) {
//            override fun setVisible(value: Boolean) {
//                super.setVisible(value)
//                if (value) {
//                    onCloseRequest(file)
//                }
//            }
//        }
//    },
//    dispose = FileDialog::dispose
//)



expect fun getPlatformName(): String