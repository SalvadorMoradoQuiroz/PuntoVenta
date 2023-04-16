package com.smq.puntoventa.data.models

import com.google.gson.annotations.SerializedName

data class DetalleVenta(
    @field:SerializedName("idDetalleVenta")
    val idDetalleVenta : Int? = null,

    @field:SerializedName("idVentaComp")
    val idVentaComp: String? = null,

    @field:SerializedName("idProducto")
    val idProducto: Int? = null,

    @field:SerializedName("cantidad")
    val cantidad: Int? = null
)