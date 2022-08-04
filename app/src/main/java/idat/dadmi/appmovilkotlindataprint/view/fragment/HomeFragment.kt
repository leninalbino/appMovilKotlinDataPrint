package idat.dadmi.appmovilkotlindataprint.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import idat.dadmi.appmovilkotlindataprint.databinding.FragmentHomeBinding
import idat.dadmi.appmovilkotlindataprint.view.adapter.ProductoAdapter
import idat.dadmi.appmovilkotlindataprint.viewmodel.ProductosViewModel


class HomeFragment : Fragment() {
        private var _binding:FragmentHomeBinding? = null
        private val binding get() =_binding!!
        private lateinit var productoviewModel:ProductosViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        binding.rvproducto.layoutManager = LinearLayoutManager(
            requireActivity())
        productoviewModel = ViewModelProvider(requireActivity()).get(ProductosViewModel::class.java)
        listarProductos()
        return binding.root
        //inflater.inflate(R.layout.fragment_home, container, false)
    }
    private fun listarProductos(){
        productoviewModel.listarProducto().observe(viewLifecycleOwner,
        Observer {
            binding.rvproducto.adapter=ProductoAdapter(it)
        })
    }






}