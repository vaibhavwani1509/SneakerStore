package com.otta.sneakerdetails.domain

import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject

/**
 * Use-Case for checking weather sneaker is already added to the cart or not..
 * Takes : Sneaker Id.
 * Returns : [Boolean]
 */


class CheckIsAlreadyInTheCart@Inject constructor(
    private val sneakersRepository: SneakersRepository
){
    operator fun invoke(sneakerId: String) : Boolean {
        return sneakersRepository.isAddedInTheCart(sneakerId)
    }
}