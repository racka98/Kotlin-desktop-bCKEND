import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Clear
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
import javax.swing.text.StyleConstants.Alignment

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
            var mSelectedText by remember { mutableStateOf("") }
            Text("Select Valid identification :", textAlign = TextAlign.Center)
            Column(Modifier.padding(20.dp)) {
                OutlinedTextField(
                    value = mSelectedText,
                    onValueChange = {
                        mSelectedText = it
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { mSelectedText = "" }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear"
                            )
                        }

                    }
                )
            }


            //Choose File ******************************************************************************************
            Column(
                modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                }) {
                    Text("Choose File")
                }
            }
            //Submit Button ******************************************************************************************
                Column(
                    modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {
                    }) {
                        Text("Submit")
                    }
                }
            }


        }

        }

expect fun getPlatformName(): String