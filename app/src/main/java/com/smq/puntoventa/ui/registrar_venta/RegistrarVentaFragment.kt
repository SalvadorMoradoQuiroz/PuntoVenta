package com.smq.puntoventa.ui.registrar_venta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smq.puntoventa.R
import com.smq.puntoventa.data.models.Cliente
import com.smq.puntoventa.data.models.Producto
import com.smq.puntoventa.data.models.ProductoAdd
import com.smq.puntoventa.data.models.Venta
import com.smq.puntoventa.databinding.FragmentRegistrarVentaBinding
import com.smq.puntoventa.util.adapters.AdapterListProductosAdd
import com.smq.puntoventa.util.adapters.AdapterListVentas
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.ArrayList

@AndroidEntryPoint
class RegistrarVentaFragment : Fragment() {

    private var _binding: FragmentRegistrarVentaBinding? = null
    private val binding get() = _binding!!
    private val registrarVentaViewModel: RegistrarVentaViewModel by viewModels()

    private lateinit var listProducts: ArrayList<Producto>
    private lateinit var listClientes: ArrayList<Cliente>
    private var productosVenta = ""

    private lateinit var listProductosAdd: ArrayList<ProductoAdd>
    private lateinit var adapter: AdapterListProductosAdd

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentRegistrarVentaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listProducts = ArrayList<Producto>()
        listClientes = ArrayList<Cliente>()

        binding.recyclerViewProductos.layoutManager = LinearLayoutManager(root.context)
        listProductosAdd = ArrayList<ProductoAdd>()
        adapter = AdapterListProductosAdd(root.context, listProductosAdd)
        binding.recyclerViewProductos.adapter = adapter

        registrarVentaViewModel.onCreate()

        binding.fab.setOnClickListener(){
            findNavController().navigate(R.id.action_RegistrarVentas_to_Ventas)
        }

        binding.buttonAddProduct.setOnClickListener(){
            val nombreP = binding.spinnerProduct.text.toString()
            if(nombreP.isNotBlank() && nombreP.isNotEmpty()){
                val cant = binding.editTextCant.text.toString()
                if(cant.isNotBlank() && cant.isNotEmpty()){
                    val idP = buscarIdProducto(nombreP)
                    productosVenta += "$idP,$cant-"

                    listProductosAdd.add(ProductoAdd(nombreP, cant))
                    adapter.notifyDataSetChanged()

                    binding.spinnerProduct.setText("")
                    binding.editTextCant.setText("")

                    Log.e("productosVenta", productosVenta.substring(0,productosVenta.length-1))
                }
            }
        }

        binding.buttonRegistrarVenta.setOnClickListener(){
            if(productosVenta.isNotEmpty() && productosVenta.isNotBlank()){
                val nombreCliente = binding.spinnerClient.text.toString()
                if(nombreCliente.isNotBlank() && nombreCliente.isNotEmpty()){
                    val idCliente = buscarIdCliente(nombreCliente)
                    productosVenta = productosVenta.substring(0,productosVenta.length-1)
                    registrarVentaViewModel.registrarVenta(productosVenta, idCliente)

                    listProductosAdd.clear()
                    adapter.notifyDataSetChanged()
                    binding.spinnerProduct.setText("")
                    binding.editTextCant.setText("")
                    binding.spinnerClient.setText("")
                }
            }
        }

        registrarVentaViewModel.listProductos.observe(viewLifecycleOwner, Observer {
            listProducts = it as ArrayList<Producto>
            var auxList = ArrayList<String>()
            for(i in 0..it.size-1){
                it.get(i).descripcion?.let { it1 -> auxList.add(it1) }
            }
            val adapterSpinner = ArrayAdapter<String>(root.context, android.R.layout.simple_dropdown_item_1line, auxList)
            binding.spinnerProduct.setAdapter(adapterSpinner)
        })

        registrarVentaViewModel.listClientes.observe(viewLifecycleOwner, Observer {
            listClientes = it as ArrayList<Cliente>
            var auxList = ArrayList<String>()
            for(i in 0..it.size-1){
                it.get(i).nombre?.let { it1 -> auxList.add(it1) }
            }
            val adapterSpinner = ArrayAdapter<String>(root.context, android.R.layout.simple_dropdown_item_1line, auxList)
            binding.spinnerClient.setAdapter(adapterSpinner)
        })

        registrarVentaViewModel.message.observe(viewLifecycleOwner, Observer {
            if(it=="success"){
                Toast.makeText(root.context, "Venta registrada con exito", Toast.LENGTH_SHORT).show()
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun buscarIdProducto(nombre:String) : Int{
        for(i in 0..listProducts.size-1){
            if(listProducts.get(i).descripcion == nombre){
                return listProducts.get(i).idProducto!!
                break
            }
        }
        return 0
    }

    fun buscarIdCliente(nombre:String) : Int{
        for(i in 0..listClientes.size-1){
            if(listClientes.get(i).nombre == nombre){
                return listClientes.get(i).idCliente!!
                break
            }
        }
        return 0
    }

}