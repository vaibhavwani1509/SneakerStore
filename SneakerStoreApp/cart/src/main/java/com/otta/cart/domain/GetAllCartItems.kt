package com.otta.cart.domain

import com.otta.common.domain.model.Sneaker
import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject

class GetAllCartItems @Inject constructor(
    private val sneakersRepository : SneakersRepository
){
    operator fun invoke() : List<Sneaker> {
        return sneakersRepository.getCartItems()
    }
}