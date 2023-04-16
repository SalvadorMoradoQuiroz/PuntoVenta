package com.smq.puntoventa.data.network

import com.smq.puntoventa.data.models.Cliente
import com.smq.puntoventa.data.models.DetalleVenta
import com.smq.puntoventa.data.models.Producto
import com.smq.puntoventa.data.models.Venta
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PuntoVentaApiClient {
    @FormUrlEncoded
    @POST("PuntoVenta/Services/ProductosService.php")
    suspend fun getProductos(
        @Field("code") code: String,
    ): Response<List<Producto>>

    @FormUrlEncoded
    @POST("PuntoVenta/Services/ClientesService.php")
    suspend fun getClientes(
        @Field("code") code: String,
    ): Response<List<Cliente>>

    @FormUrlEncoded
    @POST("PuntoVenta/Services/VentasService.php")
    suspend fun getVentas(
        @Field("code") code: String,
    ): Response<List<Venta>>

    @FormUrlEncoded
    @POST("PuntoVenta/Services/VentasService.php")
    suspend fun getDetalleVenta(
        @Field("code") code: String,
        @Field("idVentaComp") idVentaComp: String
    ): Response<List<DetalleVenta>>

    @FormUrlEncoded
    @POST("PuntoVenta/Services/VentasService.php")
    suspend fun altaVenta(
        @Field("code") code: String,
        @Field("fecha") fecha: String,
        @Field("idCliente") idCliente: Int,
        @Field("idTipoPago") idTipoPago: Int,
        @Field("productos") productos: String
    ): Response<String>
}