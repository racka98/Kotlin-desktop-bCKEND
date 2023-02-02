import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import java.awt.Color
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
            //id Text Area **************************************************************************************
            var text1 by remember { mutableStateOf("") }
            Column(Modifier.padding(20.dp)){
                Text("ID ", textAlign = TextAlign.Center)
                OutlinedTextField(
                    value=text1,
                    onValueChange = { text1 = it},
                    label = { Text("id")}
                )
            }

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
            var isFileChooserOpen by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    selectFile(sourcePath)
                    println(sourcePath.value)

                    isFileChooserOpen = true
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
                    }) {
                        Text("Submit")
                    }
                }
            }
        }
}


//@Composable
//fun MyContent(){
//    var mExpanded by remember { mutableStateOf(false) }
//    var mSelectedText by remember { mutableStateOf("") }
//
//    var mTextFieldSize by remember { mutableStateOf("")}
//    val icon = if (mExpanded)
//        Icons.Filled.KeyboardArrowUp
//    else
//        Icons.Filled.KeyboardArrowDown
//
//    Column(Modifier.padding(20.dp)) {
//        OutlinedTextField(
//            value = mSelectedText,
//            onValueChange = { mSelectedText = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .onGloballyPositioned { coordinates ->
//                    mTextFieldSize = coordinates.size.toSize().toString()
//                },
//            label = {Text("Label")},
//            trailingIcon = {
//                Icon(icon,"contentDescription",
//                    Modifier.clickable { mExpanded = !mExpanded })
//            }
//        )
//        DropdownMenu(
//            ,
//            onDismissRequest = { mExpanded = false },
//            modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
//        )
//    }
//}

//@Composable
//fun DropdownMenu(expanded: Boolean, onDismissRequest: () -> Unit, modifier: Any, function: () -> Unit) {
//    val listItems = arrayOf("Aadhaar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
//        listItems.forEach { label ->
//            DropdownMenuItem(onClick = {
//                mSelectedText = label
//                mExpanded = false
//            }) {
//                Text(text = label)
//            }
//        }
//    }

fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit
) {
}

@Composable
fun DropdownMenuItem(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    colors: MenuItemColors = MenuDefaults.itemColors(),
    contentPadding: PaddingValues = MenuDefaults.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Unit {
}

@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Aadhaar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(androidx.compose.ui.Alignment.TopStart)) {
        Text(items[selectedIndex],modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(
            Color.GRAY))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(
                Color.RED)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }
                ){
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}

expect fun getPlatformName(): String