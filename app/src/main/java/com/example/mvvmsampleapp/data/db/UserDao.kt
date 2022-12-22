package com.example.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import com.example.mvvmsampleapp.data.db.entities.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User)

    @Query("SELECT * from User WHERE uid=$CURRENT_USER_ID")
    fun getUser():LiveData<User>


}