package com.otta.cart.data

import com.otta.cart.domain.GetAllCartItems
import com.otta.cart.domain.GetOrderDetails
import com.otta.cart.domain.RemoveItemFromCart
import com.otta.common.domain.repository.SneakersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CartModule {

    @Provides
    @ViewModelScoped
    fun provideGetAllCartItems(sneakersRepository: SneakersRepository): GetAllCartItems =
        GetAllCartItems(sneakersRepository)

    @Provides
    @ViewModelScoped
    fun provideGetOrderDetails(sneakersRepository: SneakersRepository): GetOrderDetails =
        GetOrderDetails(sneakersRepository)

    @Provides
    @ViewModelScoped
    fun provideRemoveItemFromCart(sneakersRepository: SneakersRepository): RemoveItemFromCart =
        RemoveItemFromCart(sneakersRepository)
}