package com.henrydev.registerapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {

    abstract fun itemDao() : ItemDao


    companion object {

        @Volatile
        private var Instance: RegisterDatabase? = null

        fun getDatabase(context: Context): RegisterDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    RegisterDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { db ->
                        Instance = db
                    }
            }
        }


    }


}