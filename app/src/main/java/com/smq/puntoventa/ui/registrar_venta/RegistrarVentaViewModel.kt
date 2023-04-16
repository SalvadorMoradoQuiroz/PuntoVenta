package com.smq.puntoventa.ui.registrar_venta

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smq.puntoventa.data.network.PuntoVentaService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrarVentaViewModel @Inject constructor(
    private val puntoVentaService: PuntoVentaService
) : ViewModel() {

    fun onCreate() {
        viewModelScope.launch {
            //val response = puntoVentaService.getProductos("allProductos")
            //val response = puntoVentaService.getClientes("allClientes")
            //val response = puntoVentaService.altaVenta("altaVenta", "2023-04-15,20:40:51", 2, 2, "1,2-2,3")
            //Log.e("RESPONSE", response)
        }
    }
}