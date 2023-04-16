package com.smq.puntoventa.ui.ventas

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.smq.puntoventa.R
import com.smq.puntoventa.data.models.DetalleVenta
import com.smq.puntoventa.data.models.Venta
import com.smq.puntoventa.databinding.FragmentVentasBinding
import com.smq.puntoventa.util.adapters.AdapterListVentas
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VentasFragment : Fragment() {

    private var _binding: FragmentVentasBinding? = null
    private val binding get() = _binding!!
    private val ventasViewModel: VentasViewModel by viewModels()
    private lateinit var listVentas: ArrayList<Venta>
    private lateinit var adapter: AdapterListVentas

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentVentasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewVentas.layoutManager = LinearLayoutManager(root.context)
        listVentas = ArrayList<Venta>()
        adapter = AdapterListVentas(root.context, listVentas)
        binding.recyclerViewVentas.adapter = adapter

        ventasViewModel.onCreate()

        ventasViewModel.listVentas.observe(viewLifecycleOwner, Observer {
            listVentas.clear()
            listVentas.addAll(it)
            adapter.notifyDataSetChanged()
        })

        ventasViewModel.detalleVenta.observe(viewLifecycleOwner, Observer {
            showDialogDetalleVenta(it)
        })

        adapter.onItemClick = {
            ventasViewModel.consultarDetalleVenta("${it.idCliente},${it.fecha}")
        }

        return root
    }

    private fun showDialogDetalleVenta(detalleVenta: List<DetalleVenta>) {
        var aux = ""
        for(i in 0..detalleVenta.size-1){
            aux += "Id Producto: " + detalleVenta.get(i).idProducto+"\n" +
                    "Cantidad: " + detalleVenta.get(i).cantidad+"\n\n"
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val view: View = layoutInflater.inflate(R.layout.alert_dialog_detalle_venta, null)
        builder.setView(view)

        val dialog: Dialog = builder.create()
        dialog.show()

        val textViewDetalleVenta: TextView = view.findViewById(R.id.textViewDetalleVenta)
        textViewDetalleVenta.setText(aux)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}