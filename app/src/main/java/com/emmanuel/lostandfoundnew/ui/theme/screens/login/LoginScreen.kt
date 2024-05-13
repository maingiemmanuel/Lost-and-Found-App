package com.emmanuel.lostandfoundnew.ui.theme.screens.login

import AuthViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow.Companion.Visible
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility.Companion.Invisible
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emmanuel.lostandfoundnew.R
import com.emmanuel.lostandfoundnew.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var context = LocalContext.current
    val mylogin = AuthViewModel(navController, context)
    var showPassword by rememberSaveable { mutableStateOf(false) }
    mylogin.isLoggedIn()
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
    }
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
            colorFilter = ColorFilter.tint(Color.Black, BlendMode.SrcIn)
        )
        Text(text = "Login",
            color = Color.Green,
            fontFamily = FontFamily.Default,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = email,
            onValueChange = {email=it},
            label = { Text(text = "Enter Email", color = Color.Black)},
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter Password", color = Color.Black) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(onClick = {
            val mylogin = AuthViewModel(navController, context)
        mylogin.signIn(email.text.trim(), password.text.trim())},
            modifier = Modifier
                .fillMaxWidth(0.5F)) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "No account yet?Register with us",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp)
        TextButton(onClick = {navController.navigate(ROUTE_REGISTER)}) {
            Text(text = "Register",
                color = Color.Green)
        }

    }
}
@Preview
@Composable
private fun Loginprev() {
    LoginScreen(rememberNavController())
}