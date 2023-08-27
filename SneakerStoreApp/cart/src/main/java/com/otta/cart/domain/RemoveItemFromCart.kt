package com.otta.cart.domain

import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject

class RemoveItemFromCart @Inject constructor(
    private val sneakersRepository : SneakersRepository
){
    operator fun invoke(sneakerId: String) {
        return sneakersRepository.removeFromCart(sneakerId)
    }
}