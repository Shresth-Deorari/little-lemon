package com.example.littlelemon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.littlelemon.database.dao.MenuDAO
import com.example.littlelemon.database.entity.MenuEntity

@Database(entities = [MenuEntity::class], version = 1)
abstract class MenuItemDatabase : RoomDatabase(){

    abstract fun menuDao() : MenuDAO

    companion object{
        @Volatile
        private var INSTANCE : MenuItemDatabase? = null

        fun getDatabase(context : Context) : MenuItemDatabase{
            if(INSTANCE==null){
                //lock threads to prevent creating multiple instances
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MenuItemDatabase::class.java,
                        "menuDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }

    }

}