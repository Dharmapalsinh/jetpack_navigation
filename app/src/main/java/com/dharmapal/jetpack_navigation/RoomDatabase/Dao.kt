
package com.dharmapal.jetpack_navigation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dharmapal.gatetouch_task.MyMovies


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(User: User?)

    @Query("SELECT * FROM User ")
    fun getUsers():List<User>
}