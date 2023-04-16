package com.smq.puntoventa.data.models

import com.google.gson.annotations.SerializedName

data class Producto(
    @field:SerializedName("idProducto")
    val idProducto: Int? = null,

    @field:SerializedName("descripcion")
    val descripcion: String? = null,

    @field:SerializedName("precio")
    val precio: Double? = null,
)