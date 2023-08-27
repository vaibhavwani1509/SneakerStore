package com.otta.sneakerslist.domain

import com.otta.common.domain.model.Sneaker
import com.otta.common.domain.repository.SneakersRepository
import javax.inject.Inject

/**
 * USE-CASE for getting all the sneakers that matches with keyword.
 * Takes no argument.
 * Returns List<[Sneaker]>
 */

class SearchSneaker @Inject constructor(
    private val sneakersRepository: SneakersRepository
) {
    operator fun invoke(keyword: String): List<Sneaker> {
        return sneakersRepository.getFilteredSneakers(keyword)
    }
}