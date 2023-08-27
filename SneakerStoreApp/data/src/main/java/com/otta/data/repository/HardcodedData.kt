package com.otta.data.repository

import com.otta.common.domain.model.Media
import com.otta.common.domain.model.Sneaker

object HardcodedData {

    const val sneakerName = "Nike Air"

    const val sneakerDetails = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et."

    fun generate100Sneakers(): List<Sneaker> {
        val list = mutableListOf<Sneaker>()
        for (i in 0 until 100) {
            list.add(
                Sneaker(
                    brand = "Nike",
                    colorway = "White",
                    gender = "Male",
                    id = i.toString(),
                    media = Media(
                        imageUrl = "https://lorem-picsum.com/img/sneaker.jpg",
                        smallImageUrl = "https://lorem-picsum.com/img/sneaker.jpg",
                        thumbUrl = "https://lorem-picsum.com/img/sneaker.jpg"
                    ),
                    name = sneakerName,
                    releaseDate = "2023",
                    retailPrice = i * 75,
                    shoe = sneakerDetails,
                    styleId = "007",
                    title = sneakerDetails,
                    year = 2023
                )
            )
        }

        return list
    }

}