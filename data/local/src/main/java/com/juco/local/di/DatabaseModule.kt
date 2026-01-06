package com.juco.local.di

import android.content.Context
import androidx.room.Room
import com.juco.local.dao.SubscriptionDao
import com.juco.local.dao.UserDao
import com.juco.local.database.SubManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SubManagerDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = SubManagerDatabase::class.java,
            name = "submanager-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: SubManagerDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideSubscriptionDao(database: SubManagerDatabase): SubscriptionDao {
        return database.subscriptionDao()
    }
}