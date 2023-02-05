package com.myapplication.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.myapplication.common.database.ReminderEntityDao
import com.myapplication.common.database.getDatabase
import com.myapplication.common.model.Reminder

@Composable
fun App(contextProvider: ContextProvider) {
    val reminderDao = remember { ReminderEntityDao(db = getDatabase(contextProvider)) }
    val reminders = reminderDao.selectAll().collectAsState(emptyList())
    AppContents(
        reminders = reminders,
        onSubmit = reminderDao::insert // This is called a member reference. See: https://stackoverflow.com/a/59823628/15285215
    )
}

@Composable
fun AppContents(
    reminders: State<List<Reminder>>,
    onSubmit: (Reminder) -> Unit
) {
    val scrollState = rememberScrollState()
    MaterialTheme {
        // TODO: Consider making this a LazyColumn
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            //id Text Area ****************************************************************
            /**
             * ID is auto-generated. We likely don't need this input
             */
            /*var text1 by remember { mutableStateOf("") }
            Column(Modifier.padding(20.dp)) {
                Text("ID ", textAlign = TextAlign.Center)
                OutlinedTextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = { Text("id") }
                )
            }*/

            //Full Name Text Area **************************************************************************************
            var fullNameText by remember { mutableStateOf("") }
            Column(Modifier.padding(20.dp)) {
                Text("Full Name", textAlign = TextAlign.Center)
                OutlinedTextField(
                    value = fullNameText,
                    onValueChange = { fullNameText = it },
                    label = { Text("Full Name") }
                )
            }

            //Password Text Area ***********************************************************************************
            var password by remember { mutableStateOf("") }
            Column(Modifier.padding(20.dp)) {
                Text("Enter Your Password", textAlign = TextAlign.Center)
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
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

            var mTextFieldSize by remember { mutableStateOf(" ") }

            var mSelectedText by remember { mutableStateOf("") }
            Column(Modifier.padding(20.dp)) {
                Text("Select Valid Identification ", textAlign = TextAlign.Center)
                OutlinedTextField(
                    value = mSelectedText,
                    onValueChange = { mSelectedText = it },
                    modifier = Modifier.fillMaxWidth()
                        .onGloballyPositioned { coordinates -> mTextFieldSize = coordinates.size.toSize().toString() },
                    label = { Text("Label") },
                    trailingIcon = {
                        Icon(icon, "contentDescription", Modifier.clickable { mExpanded = !mExpanded })
                    }
                )

            }
            // TODO: ExposedDropdownMenu(Context)

            //Submit Button *********************************************
            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = {
                    val reminder = Reminder(
                        id = 0L, // We put 0 because it will be auto-generated
                        name = fullNameText,
                        password = password,
                        identification = mSelectedText, //TODO: You should probably complete this
                        data = null //TODO: I don't know what you want to put in this field
                    )
                    onSubmit(reminder)

                    // Reset the fields
                    fullNameText = ""
                    password = ""
                    mSelectedText = ""
                }
            ) {
                Text("Submit")
            }

            // All Saved Reminders
            RemindersList(reminders)
        }
    }
}

/**
 * Display all the saved Reminders
 */
@Composable
fun ColumnScope.RemindersList(
    reminders: State<List<Reminder>>
) {
    Text(
        text = "Saved Reminders",
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(20.dp)
    )

    if (reminders.value.isEmpty()) {
        Text(
            text = "Saved Reminders",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )
    }

    reminders.value.forEachIndexed { _, reminder ->
        Text(text = "ID: ${reminder.id}", modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Full Name: ${reminder.name.ifBlank { "None" }}",
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Password: ${reminder.password.ifBlank { "None" }}",
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Identification: ${reminder.identification.ifBlank { "None" }}",
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(8.dp))
        Divider(modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(Modifier.height(20.dp))
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
//        com.myapplication.common.DropdownMenu(
//            ,
//            onDismissRequest = { mExpanded = false },
//            modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
//        )
//    }
//}

//@Composable
//fun com.myapplication.common.DropdownMenu(expanded: Boolean, onDismissRequest: () -> Unit, modifier: Any, function: () -> Unit) {
//    val listItems = arrayOf("Aadhaar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
//        listItems.forEach { label ->
//            com.myapplication.common.DropdownMenuItem(onClick = {
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
    //properties: PopupProperties = PopupProperties(focusable = true), // Won't work in commonMain
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
    //colors: MenuItemColors = MenuDefaults.itemColors(), // Won't work in commonMain
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
        Text(
            items[selectedIndex],
            modifier = Modifier.fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(Color.Gray)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(color = Color.Red)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }
                ) {
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