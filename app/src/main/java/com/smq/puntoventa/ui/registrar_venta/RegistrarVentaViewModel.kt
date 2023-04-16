package com.smq.puntoventa.ui.registrar_venta

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smq.puntoventa.data.models.Cliente
import com.smq.puntoventa.data.models.Producto
import com.smq.puntoventa.data.network.PuntoVentaService
import com.smq.puntoventa.util.helpers.TimeHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrarVentaViewModel @Inject constructor(
    private val puntoVentaService: PuntoVentaService
) : ViewModel() {

    val listProductos = MutableLiveData<List<Producto>>()
    val listClientes = MutableLiveData<List<Cliente>>()
    val message = MutableLiveData<String>()

    fun onCreate() {
        viewModelScope.launch {
            val productos = puntoVentaService.getProductos("allProductos")
            val clientes = puntoVentaService.getClientes("allClientes")

            listProductos.postValue(productos)
            listClientes.postValue(clientes)
        }
    }

    fun registrarVenta(productosVenta:String, idCliente:Int) {
        viewModelScope.launch {
            val response = puntoVentaService.altaVenta("altaVenta", TimeHelper.obtenerFecha(), idCliente, 1, productosVenta)
            Log.e("RESPONSE", response)
            message.postValue(response)
        }
    }
}