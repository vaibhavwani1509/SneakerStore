package com.otta.sneakerdetails.domain

import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject

/**
 * Use-Case for adding sneaker to cart.
 * Takes : Sneaker Id.
 * Returns : Nothing
 */

class AddSneakerToCart @Inject constructor(
    private val sneakersRepository: SneakersRepository
){
    operator fun invoke(sneakerId: String){
        return sneakersRepository.addToCart(sneakerId)
    }
}