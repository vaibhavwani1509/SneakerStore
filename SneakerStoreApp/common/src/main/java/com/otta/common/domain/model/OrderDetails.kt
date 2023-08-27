package com.otta.common.domain.model

data class OrderDetails(
    val SubTotal: Int,
    val TaxesAndCharges: Int,
    val Total: Int,
)
