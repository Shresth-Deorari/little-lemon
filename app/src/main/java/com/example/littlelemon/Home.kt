package com.example.littlelemon

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.littlelemon.database.MenuItemDatabase
import com.example.littlelemon.database.entity.MenuEntity
import com.example.littlelemon.repository.GetRepo
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController : NavHostController){
    val context : Context = LocalContext.current
    val database = MenuItemDatabase.getDatabase(context)
    val menuDao = database.menuDao()
    val lifecycleOwner = LocalLifecycleOwner.current

    val menuItems by menuDao.getMenuItem().observeAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit){
        lifecycleOwner.lifecycleScope.launch {
            try{
                val menuList = GetRepo().getMenuItems()

                val menuEntities = menuList.map { item->
                    MenuEntity(
                        id = item.id,
                        title = item.title,
                        description = item.description,
                        price = item.price,
                        image = item.image,
                        category = item.category
                    )
                }
                menuEntities.forEach { menuItem->
                    menuDao.insertMenuItem(menuItem)
                }
            }
            catch (e : Exception){
                Log.e("HomeScreen", "Error fetching data: ${e.message}")
            }
        }


    }

}