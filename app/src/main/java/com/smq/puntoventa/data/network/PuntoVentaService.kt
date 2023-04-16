package com.smq.puntoventa.data.network

import com.smq.puntoventa.data.models.Cliente
import com.smq.puntoventa.data.models.DetalleVenta
import com.smq.puntoventa.data.models.Producto
import com.smq.puntoventa.data.models.Venta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PuntoVentaService @Inject constructor(private val api:PuntoVentaApiClient) {

    suspend fun getProductos(code:String) : List<Producto>{
        return withContext(Dispatchers.IO) {
            val response = api.getProductos(code)
            response.body()!!
        }
    }

    suspend fun getClientes(code:String) : List<Cliente>{
        return withContext(Dispatchers.IO) {
            val response = api.getClientes(code)
            response.body()!!
        }
    }

    suspend fun getVentas(code:String) : List<Venta>{
        return withContext(Dispatchers.IO) {
            val response = api.getVentas(code)
            response.body()!!
        }
    }

    suspend fun getDetalleVenta(code:String, idVentaComp:String) : List<DetalleVenta>{
        return withContext(Dispatchers.IO) {
            val response = api.getDetalleVenta(code, idVentaComp)
            response.body()!!
        }
    }

    suspend fun altaVenta(code:String, fecha:String, idCliente:Int, idTipoPago:Int, productos:String) : String {
        return withContext(Dispatchers.IO) {
            val response = api.altaVenta(code, fecha, idCliente, idTipoPago, productos)
            response.body()!!
        }
    }
}