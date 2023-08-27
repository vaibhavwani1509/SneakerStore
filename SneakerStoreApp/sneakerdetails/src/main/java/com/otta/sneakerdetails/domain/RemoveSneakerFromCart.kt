package com.otta.sneakerdetails.domain

import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject


/**
 * Use-Case for removing a  sneaker from the cart.
 * Takes : Sneaker Id.
 * Returns : Nothing
 */


class RemoveSneakerFromCart @Inject constructor(
    private val sneakersRepository: SneakersRepository
){
    operator fun invoke(sneakerId: String) {
        return sneakersRepository.removeFromCart(sneakerId)
    }
}