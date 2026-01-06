package com.juco.local.di

import com.juco.local.repository.LocalRepository
import com.juco.local.repository.LocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}