package com.smq.puntoventa.data.models

import com.google.gson.annotations.SerializedName

data class Cliente(
    @field:SerializedName("idCliente")
    val idCliente: Int? = null,

    @field:SerializedName("nombre")
    val nombre: String? = null,

    @field:SerializedName("apellido")
    val apellido: String? = null,

    @field:SerializedName("telefono")
    val telefono: String? = null,
)