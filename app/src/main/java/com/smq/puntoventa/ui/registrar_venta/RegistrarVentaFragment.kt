package com.smq.puntoventa.ui.registrar_venta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smq.puntoventa.R
import com.smq.puntoventa.databinding.FragmentRegistrarVentaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrarVentaFragment : Fragment() {

    private var _binding: FragmentRegistrarVentaBinding? = null
    private val binding get() = _binding!!
    private val registrarVentaViewModel: RegistrarVentaViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentRegistrarVentaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        registrarVentaViewModel.onCreate()

        binding.fab.setOnClickListener(){
            findNavController().navigate(R.id.action_RegistrarVentas_to_Ventas)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}