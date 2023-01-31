import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import java.io.File
import javax.naming.Context





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
            val listItems = arrayOf("Aadhaar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
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
            Column(
                modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                }) {
                    Text("Choose File")
                }
            }
            //Submit Button ******************************************************************************************
            val sourcePath = remember { mutableStateOf<String?>(null) }
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
                        val destination:String = destinePath as String

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



expect fun getPlatformName(): String