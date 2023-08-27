package com.otta.cart.domain

import com.otta.common.domain.model.OrderDetails
import com.otta.common.domain.repository.SneakersRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetOrderDetails@Inject constructor(
    private val sneakersRepository : SneakersRepository
){
    suspend operator fun invoke() : OrderDetails {
        delay(100)
        return sneakersRepository.getOrderDetails()
    }
}