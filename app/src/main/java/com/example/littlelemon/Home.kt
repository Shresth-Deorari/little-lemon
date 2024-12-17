package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    getMenu: suspend () -> List<MenuItem>
) {
    val scope = rememberCoroutineScope()
    var menuItems by remember { mutableStateOf<List<MenuItem>>(emptyList()) }
    var filteredItems by remember { mutableStateOf(menuItems) }
    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        scope.launch {
            menuItems = getMenu()
            filteredItems = menuItems
        }
    }

        Column(modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
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
            },

        ) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround){
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .height(70.dp)
                        .padding(top = 15.dp, bottom = 15.dp, start = 50.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxWidth(.3f)
                        .height(70.dp)
                        .clickable {
                            navController.navigate(Screen.Profile.route)
                        }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF495E57))
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = "Little Lemon",
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.primary)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 40.sp,
                    color = Color(0xFFF4CE14),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Chicago",
                    fontSize = 24.sp,
                    color = Color.White
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.Top
                ) {

                    Text(
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier
                            .weight(5f)
                            .padding(top = 10.dp,end = 8.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Restaurant Image",
                        modifier = Modifier
                            .weight(4f)
                            .height(150.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

            }

            OutlinedTextField(
                label = { Text(text = "Enter search phrase") },
                value = searchPhrase,
                onValueChange = { phrase ->
                    searchPhrase = phrase
                    filteredItems = menuItems.filter {
                        it.title.contains(phrase.trim().lowercase(), ignoreCase = true)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(onGo = { focusManager.clearFocus() })
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val categories = listOf("Starters", "Mains", "Desserts", "Drinks")
                categories.forEach { category ->
                    ElevatedCard(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                selectedCategory = if (selectedCategory == category) "" else category
                                filteredItems = if (selectedCategory.isNotEmpty()) {
                                    menuItems.filter { it.category.equals(category, ignoreCase = true) }
                                } else menuItems
                            },
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color(0xFF495E57)
                        )
                    ) {
                        Text(
                            text = category,
                            modifier = Modifier.padding(8.dp),
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            MenuItems(items = filteredItems)
        }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItem>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        items(items) { menuItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = menuItem.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = menuItem.description,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "$${menuItem.price}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                GlideImage(
                    model = menuItem.image,
                    contentDescription = menuItem.title,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(start = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            HorizontalDivider()
        }
    }
}
