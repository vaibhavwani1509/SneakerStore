package com.otta.di

import com.otta.common.domain.repository.SneakersRepository
import com.otta.data.repository.SneakersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideSneakerRepository(): SneakersRepository = SneakersRepositoryImpl
}