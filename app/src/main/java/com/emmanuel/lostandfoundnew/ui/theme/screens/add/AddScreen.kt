package com.emmanuel.lostandfoundnew.ui.theme.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emmanuel.lostandfoundnew.data.ReportData
import com.emmanuel.lostandfoundnew.data.ReportViewModel


@Composable
fun AddScreen(navController: NavHostController, reportViewModel: ReportViewModel = viewModel()) {

    var itemName by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var contactInfo by remember { mutableStateOf(TextFieldValue()) }
    var date by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add Details of Lost or Found Item",
            color = Color.Black,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        ItemTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = "Item Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ItemTextField(
            value = description,
            onValueChange = { description = it },
            label = "Description"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ItemTextField(
            value = contactInfo,
            onValueChange = { contactInfo = it },
            label = "Contact Info"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Date
        ItemTextField(
            value = date,
            onValueChange = { date = it },
            label = "Date"
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                itemName.let { item ->
                    val reportData = ReportData(
                        itemName = itemName.text,
                        description = description.text,
                        contactInfo = contactInfo.text,
                        date = date.text,
                    )
                    reportViewModel.addReport(reportData)
                    // Navigate back to the previous screen
                    navController.popBackStack()
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Green),
            modifier = Modifier.fillMaxWidth(0.5F)
        ) {
            Text(text = "Submit", fontSize = 30.sp)
        }
    }
}




@Composable
private fun ItemTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) }
    )
}



@Preview
@Composable
private fun AddPrev() {
    AddScreen(rememberNavController())
}

