package com.smq.puntoventa.data.models

import com.google.gson.annotations.SerializedName

data class Venta(
    @field:SerializedName("idVenta")
    val idVenta: Int? = null,

    @field:SerializedName("fecha")
    val fecha: String? = null,

    @field:SerializedName("idCliente")
    val idCliente: Int? = null,

    @field:SerializedName("idTipoPago")
    val idTipoPago: Int? = null
)