package com.example.littlelemon.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MenuItemTable")
data class MenuEntity(
    @PrimaryKey
    val id: Int,
    val title : String,
    val description: String,
    val price: Double,
    val image : String,
    val category : String
)