package com.otta.sneakerslist.domain

import com.otta.common.domain.model.Sneaker
import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject

/**
 * USE-CASE for getting all the sneakers list from repo.
 * Takes no argument.
 * Returns List<[Sneaker]>
 */

class GetAllSneakers @Inject constructor(
    private val sneakersRepository: SneakersRepository
) {
    operator fun invoke(): List<Sneaker> {
        return sneakersRepository.getAllSneakers()
    }
}