package com.smq.puntoventa.ui.ventas

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smq.puntoventa.data.models.DetalleVenta
import com.smq.puntoventa.data.models.Venta
import com.smq.puntoventa.data.network.PuntoVentaService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VentasViewModel @Inject constructor(
    private val puntoVentaService: PuntoVentaService
) : ViewModel() {

    val listVentas = MutableLiveData<List<Venta>>()
    val detalleVenta = MutableLiveData<List<DetalleVenta>>()

    fun onCreate() {
        viewModelScope.launch {
            val response = puntoVentaService.getVentas("allVentas")
            Log.e("RESPONSE TODAS LAS VENTAS", response.toString())
            listVentas.postValue(response)

        }
    }

    fun consultarDetalleVenta(idVentaComp: String) {
        viewModelScope.launch {
            val responseDetalle = puntoVentaService.getDetalleVenta("detalleVenta", idVentaComp)
            Log.e("RESPONSE DETALLE VENTA", responseDetalle.toString())
            detalleVenta.postValue(responseDetalle)
        }
    }
}