package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.littlelemon.repository.DataStoreRepository
import com.example.littlelemon.repository.UserDetails
import kotlinx.coroutines.launch

@Composable
fun Onboarding(
    navController : NavHostController,
    dataStore : DataStoreRepository
){
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("")}
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val lastNameFocusRequester = FocusRequester()
        val emailFocusRequester = FocusRequester()
        val lifecycleOwner = LocalLifecycleOwner.current

        Column(
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    }
                    )
                }
                .pointerInput(Unit){
                    detectDragGestures { _, _ ->
                        focusManager.clearFocus()
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(15.dp)
            )
            Spacer(Modifier.padding(top=10.dp))
                Text(
                    text = "Let's get to know you",
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.primary)
                        .padding(top = 25.dp, bottom = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
                Spacer(Modifier.padding(top=40.dp))
                Text(
                    text = "Personal Information",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 15.dp),
                )
                Spacer(Modifier.padding(top=60.dp))
                Text(
                    text = "First Name",
                    modifier = Modifier.padding(start = 15.dp, bottom = 5.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {firstName = it.trimStart()},
                    modifier = Modifier.padding(start = 20.dp, bottom = 20.dp),
                    label = {Text("Enter First Name")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go, capitalization = KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(onGo = {lastNameFocusRequester.requestFocus()} )
                )
                Text(
                    text = "Last Name",
                    modifier = Modifier.padding(start = 15.dp, bottom = 5.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {lastName = it.trimStart()},
                    modifier = Modifier
                        .padding(start = 20.dp, bottom = 20.dp)
                        .focusRequester(lastNameFocusRequester),
                    label = {Text("Enter Last Name")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go, capitalization = KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(onGo = { emailFocusRequester.requestFocus() })
                )
                Text(
                    text = "Email",
                    modifier = Modifier.padding(start = 15.dp, bottom = 5.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it.trimStart()},
                    modifier = Modifier
                        .padding(start = 20.dp, bottom = 20.dp)
                        .focusRequester(emailFocusRequester),
                    label = {Text("Enter Email")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Email),
                    keyboardActions = KeyboardActions(onDone = {registerOnClick(firstName, lastName, email, context, navController, lifecycleOwner, dataStore)})
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                        registerOnClick(firstName, lastName, email, context, navController, lifecycleOwner, dataStore)
                },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 50.dp)
                        .fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(
                        text = "Register",
                        color = Color.Black,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
        }
}

fun registerOnClick(
    firstName: String,
    lastName : String,
    email : String,
    context : Context,
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    dataStore: DataStoreRepository
){
    if(firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()){
        lifecycleOwner.lifecycleScope.launch {
                val userDetails = UserDetails(
                    FIRST_NAME = firstName,
                    LAST_NAME = lastName,
                    EMAIL = email
                )
            dataStore.saveToDataStore(userDetails)
            navController.popBackStack(Screen.Onboarding.route, inclusive = true)
            navController.navigate(Screen.Home.route)
        }
    }
    else{
        Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_LONG).show()
    }
}