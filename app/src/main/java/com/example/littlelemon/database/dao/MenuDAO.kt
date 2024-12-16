package com.example.littlelemon.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.littlelemon.database.entity.MenuEntity

@Dao
interface MenuDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItem(menuItem : MenuEntity)

    @Update
    suspend fun updateMenuItem(menuItem : MenuEntity)

    @Delete
    suspend fun deleteMenuItem(menuItem : MenuEntity)

    @Query("SELECT * FROM MenuItemTable")
    fun getMenuItem() : LiveData<List<MenuEntity>>

}