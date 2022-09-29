package com.dharmapal.jetpack_navigation


import android.content.Context
import androidx.room.*

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    companion object{

        var database:UserDatabase?=null

        @Synchronized
        fun getDatabase(context: Context):UserDatabase{
            if (database==null){
                database= Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,"Users.db"
                ).build()
            }
            return database!!
        }
    }

    abstract fun dao(): Dao

}