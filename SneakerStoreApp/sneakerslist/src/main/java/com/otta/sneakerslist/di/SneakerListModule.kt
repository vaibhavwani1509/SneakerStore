package com.otta.sneakerslist.di

import com.otta.common.domain.repository.SneakersRepository

import com.otta.sneakerslist.domain.GetAllSneakers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SneakerListModule {

    @Provides
    @ViewModelScoped
    fun provideGetAllSneakers(sneakersRepository: SneakersRepository): GetAllSneakers {
        return GetAllSneakers(sneakersRepository)
    }
}