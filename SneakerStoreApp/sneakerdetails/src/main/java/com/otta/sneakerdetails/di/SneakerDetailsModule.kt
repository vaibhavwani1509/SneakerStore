package com.otta.sneakerdetails.di

import com.otta.common.domain.repository.SneakersRepository
import com.otta.sneakerdetails.domain.AddSneakerToCart
import com.otta.sneakerdetails.domain.CheckIsAlreadyInTheCart
import com.otta.sneakerdetails.domain.GetSneakerById
import com.otta.sneakerdetails.domain.RemoveSneakerFromCart
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SneakerDetailsModule {

    @Provides
    @ViewModelScoped
    fun provideAddSneakerToCart(sneakersRepository: SneakersRepository): AddSneakerToCart {
        return AddSneakerToCart(sneakersRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideCheckIsAlreadyInTheCart(sneakersRepository: SneakersRepository): CheckIsAlreadyInTheCart {
        return CheckIsAlreadyInTheCart(sneakersRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetSneakerById(sneakersRepository: SneakersRepository): GetSneakerById {
        return GetSneakerById(sneakersRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideRemoveSneakerFromCart(sneakersRepository: SneakersRepository): RemoveSneakerFromCart {
        return RemoveSneakerFromCart(sneakersRepository)
    }
}