package com.otta.data.repository

import com.otta.common.domain.model.OrderDetails
import com.otta.common.domain.model.Sneaker
import com.otta.common.domain.repository.SneakersRepository

object SneakersRepositoryImpl : SneakersRepository {

    private var cartItems = mutableListOf<Sneaker>()

    override fun getAllSneakers(): List<Sneaker> {
        return HardcodedData.generate100Sneakers()
    }

    override fun getSneakerById(sneakerId: String): Sneaker {
        return getAllSneakers().first {
            it.id == sneakerId
        }
    }

    override fun getFilteredSneakers(keyword: String): List<Sneaker> {
       return getAllSneakers().filter {
           it.name.lowercase().contains(keyword.lowercase())
       }
    }

    override fun addToCart(sneakerId: String) {
        cartItems.add(getSneakerById(sneakerId))
    }

    override fun getCartItems(): List<Sneaker> = cartItems

    override fun isAddedInTheCart(sneakerId: String): Boolean {
        val sneaker = getSneakerById(sneakerId)
        return cartItems.contains(sneaker)
    }

    override fun removeFromCart(sneakerId: String) {
        val sneaker = getSneakerById(sneakerId)
        cartItems.remove(sneaker)
    }

    override fun getOrderDetails(): OrderDetails {
        val taxAndCharges = 40
        var subTotal = 0
        cartItems.forEach { sneaker ->
            subTotal += sneaker.retailPrice
        }

        return OrderDetails(
            SubTotal = subTotal,
            TaxesAndCharges = taxAndCharges,
            Total = subTotal + taxAndCharges
        )
    }
}