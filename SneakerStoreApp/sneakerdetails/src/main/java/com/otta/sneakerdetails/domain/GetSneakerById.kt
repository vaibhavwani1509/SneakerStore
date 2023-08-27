package com.otta.sneakerdetails.domain

import com.otta.common.domain.model.Sneaker
import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject


/**
 * Use-Case for getting a single sneaker using ID.
 * Takes : Sneaker Id.
 * Returns : [Sneaker]
 */


class GetSneakerById @Inject constructor(
    private val sneakersRepository: SneakersRepository
){
    operator fun invoke(sneakerId: String) : Sneaker {
        return sneakersRepository.getSneakerById(sneakerId)
    }
}