package com.emmanuel.lostandfoundnew.ui.theme.screens.home

import AuthViewModel
import AuthViewModelFactory
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emmanuel.lostandfoundnew.R
import com.emmanuel.lostandfoundnew.navigation.ROUTE_CLAIM
import com.emmanuel.lostandfoundnew.navigation.ROUTE_CLAIM2
import com.emmanuel.lostandfoundnew.navigation.ROUTE_REPORT


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    var context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(navController, context))
    val loggedInUserEmail by authViewModel.loggedInUserEmail.observeAsState("")


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        // Profile Icon
        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
                .clickable { showMenu = !showMenu }
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Icon",
                tint = Color.Black
            )
            if (showMenu) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(8.dp)
                        .clickable { /* Handle clicks on menu items */ }
                ) {
                    Column {
                        Text(text = loggedInUserEmail, color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Logout", color = Color.Black, modifier = Modifier.clickable {
                            // Handle logout action
                            val mylogout = AuthViewModel(navController, context)
                            mylogout.logout()
                        })
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth(),
                colorFilter = ColorFilter.tint(Color.Black, BlendMode.SrcIn)
            )

            Scaffold(
                modifier = Modifier.fillMaxHeight(0.11F),
                containerColor = Color.White
            ) {
                SearchBar(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.White),
                    query = text,
                    onQueryChange = {
                        text = it
                    },
                    onSearch = {
                        active = false
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    placeholder = { Text(text = "Search") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    } else {
                                        active = false
                                    }

                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon"
                            )
                        }
                    }
                ) {
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { navController.navigate(ROUTE_CLAIM) },
                modifier = Modifier
                    .fillMaxWidth(0.6F)
            ) {
                Text(text = "Search")
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate(ROUTE_CLAIM) },
                modifier = Modifier
                    .fillMaxWidth(0.6F)
            ) {
                Text(text = "LostItems")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate(ROUTE_CLAIM2) },
                modifier = Modifier
                    .fillMaxWidth(0.6F)
            ) {
                Text(text = "FoundItems")
            }

            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = "Found a lost item?",
                color = Color.Black
            )
            Button(
                onClick = { navController.navigate(ROUTE_REPORT) },
                modifier = Modifier
                    .fillMaxWidth(0.5F)
            ) {
                Text(text = "Report")
            }
        }
    }


@Preview
@Composable
private fun HomePrev() {
    HomeScreen(rememberNavController())
}
