import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
//import androidx.compose.material.ExposedDropdownMenuBoxScope
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



@ExperimentalMaterialApi
@Composable
fun ExposedDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ExposedDropdownMenuBoxScope.() -> Unit
) {
}


@OptIn(ExperimentalMaterialApi::class)
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
            val listItems = arrayOf("Aadhaar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
            var selectedItem by remember { mutableStateOf(listItems[0]) }
            var mSelectedText by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }
        var mTextFieldSize by remember { mutableStateOf(" ")}

        Text("Select Valid Identification", textAlign = TextAlign.Center)
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates -> mTextFieldSize = coordinates.size.toSize().toString() },
                label = {Text("Label")},
                trailingIcon = {
//                    Icon(icon,"contentDescription", Modifier.clickable { mExpanded = !mExpanded })
                }
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = selectedItem,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = "Label") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // menu
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listItems.forEach { selectedOption ->
                        // menu item
                        DropdownMenuItem(onClick = {
                            selectedItem = selectedOption
                            Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT)
                                .show()
                            expanded = false
                        }) {
                            Text(text = selectedOption)
                        }
                    }
                }
            }

            //Choose File ******************************************************************************************
            Column(
                modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
//					isFileChooserOpen = true
//                    selectFile(sourcePath)
//                    println(sourcePath.value)

                }) {
                    Text("Choose File")
                }
            }
        }

        }
    }

expect fun getPlatformName(): String