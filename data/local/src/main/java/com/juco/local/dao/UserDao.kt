package com.juco.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.juco.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)
}