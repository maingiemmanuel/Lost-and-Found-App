package com.emmanuel.lostandfoundnew.ui.theme.screens.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emmanuel.lostandfoundnew.navigation.ROUTE_ADD


@Composable
fun ReportScreen(navController: NavController) {
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
                Text(
                        text = "Kindly send all the details of your lost and found item and we will get in touch with you.",
                        color = Color.Black,
                        fontSize = 25.sp
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedButton(
                        onClick = {
                                // Navigate to the AddScreen
                                navController.navigate(ROUTE_ADD)
                        },
                        colors = ButtonDefaults.buttonColors(Color.Green),
                        modifier = Modifier.fillMaxWidth(0.5F)
                ) {
                        Text(text = "Report", fontSize = 20.sp)
                }
        }
}

@Composable
private fun ReportPrev() {
        ReportScreen(rememberNavController())
}
