package com.juco.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juco.local.dao.SubscriptionDao
import com.juco.local.dao.UserDao
import com.juco.local.entity.SubscriptionEntity
import com.juco.local.entity.UserEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class,
        SubscriptionEntity::class
    ],
    exportSchema = false,
    autoMigrations = []
)

abstract class SubManagerDatabase : RoomDatabase() {
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun userDao(): UserDao
}