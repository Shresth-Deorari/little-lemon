package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.littlelemon.repository.DataStoreRepository
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController : NavHostController,
    dataStore : DataStoreRepository
){

    val firstNameFlow = dataStore.getFirstNameFromDataStore()
    val lastNameFlow = dataStore.getLastNameFromDataStore()
    val emailFlow = dataStore.getEmailFromDataStore()

    val firstName = firstNameFlow.collectAsState(initial = "")
    val lastName = lastNameFlow.collectAsState(initial = "")
    val email = emailFlow.collectAsState(initial = "")

    val lifecycleOwner = LocalLifecycleOwner.current

    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(15.dp)
        )
        Spacer(Modifier.padding(top=100.dp))
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
            value = firstName.value,
            onValueChange = {},
            modifier = Modifier.padding(start = 20.dp, bottom = 20.dp),
            enabled = false
        )
        Text(
            text = "Last Name",
            modifier = Modifier.padding(start = 15.dp, bottom = 5.dp),
            style = MaterialTheme.typography.bodySmall,
        )
        OutlinedTextField(
            value = lastName.value,
            onValueChange = {},
            modifier = Modifier
                .padding(start = 20.dp, bottom = 20.dp),
            enabled = false
        )
        Text(
            text = "Email",
            modifier = Modifier.padding(start = 15.dp, bottom = 5.dp),
            style = MaterialTheme.typography.bodySmall,
        )
        OutlinedTextField(
            value = email.value,
            onValueChange = {},
            modifier = Modifier
                .padding(start = 20.dp, bottom = 20.dp),
            enabled = false
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
                logoutOnClick(navController, lifecycleOwner, dataStore)
        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 50.dp)
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "Logout",
                color = Color.Black,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

fun logoutOnClick(
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    dataStore: DataStoreRepository
){
    lifecycleOwner.lifecycleScope.launch {
        dataStore.clearDataStore()
        navController.navigate(Screen.Onboarding.route)
    }
}