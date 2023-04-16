package com.smq.puntoventa.ui.ventas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smq.puntoventa.data.network.PuntoVentaService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VentasViewModel @Inject constructor(
    private val puntoVentaService: PuntoVentaService
) : ViewModel() {

    fun onCreate() {
        viewModelScope.launch {
            //val response = puntoVentaService.getVentas("allVentas")
            val response = puntoVentaService.getDetalleVenta("detalleVenta", "1,2023-04-15,13:40:51")
            Log.e("RESPONSE", response.toString())
        }
    }

}