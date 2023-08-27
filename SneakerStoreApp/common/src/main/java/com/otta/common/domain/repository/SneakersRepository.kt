package com.otta.common.domain.repository

import com.otta.common.domain.model.OrderDetails
import com.otta.common.domain.model.Sneaker


interface SneakersRepository {

    /** Used to get all the available Sneakers.
     * Takes : No arguments.
     * Returns : List of [Sneaker]
     */
    fun getAllSneakers(): List<Sneaker>

    /** Used to get the details of a single sneaker by using sneakerID.
     * Takes : [sneakerId].
     * Returns : Single [Sneaker]
     */
    fun getSneakerById(sneakerId: String): Sneaker


    /**
     * Used to get list of sneakers that contains the given keyword.
     * Takes : String
     * Returns : List<[Sneaker]>
     */
    fun getFilteredSneakers(keyword: String) : List<Sneaker>

    /**
     * Used to add a sneaker in the cart.
     * Takes : [sneakerId].
     * returns : Nothing
     */
    fun addToCart(sneakerId: String)

    /**
     * Used to remove the sneaker from the cart.
     * Takes : [sneakerId].
     * returns : Nothing
     */
    fun removeFromCart(sneakerId: String)

    /**
     * Used to get all the items from the cart
     * Takes : Nothing
     * returns : List of [Sneaker]
     */
    fun getCartItems(): List<Sneaker>

    /**
     * Used to check weather a sneaker is in the cart.
     * Takes : SneakerId.
     * returns : [Boolean]
     */
    fun isAddedInTheCart(sneakerId: String): Boolean

    /**
     * Used to get the final Order details such as total price, taxes and etc.
     * Takes : Nothing.
     * returns : [OrderDetails]
     */
    fun getOrderDetails(): OrderDetails


}