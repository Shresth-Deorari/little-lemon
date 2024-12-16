package com.example.littlelemon.repository

import com.example.littlelemon.network.KtorClient
import com.example.littlelemon.network.model.MenuItem
import io.ktor.client.request.*

class GetRepo {

    suspend fun getMenuItems() : List<MenuItem> = KtorClient.httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")

}