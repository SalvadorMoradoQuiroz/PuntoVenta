package com.smq.puntoventa.ui.ventas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.smq.puntoventa.databinding.FragmentVentasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VentasFragment : Fragment() {

    private var _binding: FragmentVentasBinding? = null
    private val binding get() = _binding!!
    private val vetasViewModel: VentasViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentVentasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        vetasViewModel.onCreate()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}