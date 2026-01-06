package com.juco.local.di

import android.content.Context
import androidx.room.Room
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
}