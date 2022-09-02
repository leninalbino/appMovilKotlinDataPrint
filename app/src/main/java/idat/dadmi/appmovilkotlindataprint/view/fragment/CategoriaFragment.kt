package idat.dadmi.appmovilkotlindataprint.view.fragment

import android.os.Bundle
import android.text.method.Touch.onTouchEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.FragmentCategoriaBinding
import idat.dadmi.appmovilkotlindataprint.databinding.FragmentHomeBinding
import idat.dadmi.appmovilkotlindataprint.view.adapter.CategoriaAdapter
import idat.dadmi.appmovilkotlindataprint.viewmodel.ListaCategoriaViewModel
import idat.dadmi.appmovilkotlindataprint.viewmodel.ProductosViewModel

class CategoriaFragment : Fragment() {
    private var _binding: FragmentCategoriaBinding? = null
    private val binding get() = _binding!!
    private lateinit var listaCategoriaviewModel: ListaCategoriaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriaBinding.inflate(
            inflater, container, false
        )
        binding.rvcategoria.layoutManager = LinearLayoutManager(
            requireActivity()
        )

        listaCategoriaviewModel =
            ViewModelProvider(requireActivity()).get(ListaCategoriaViewModel::class.java)

        listarCategorias()
        return binding.root
        // return inflater.inflate(R.layout.fragment_categoria, container, false)
    }

    private fun listarCategorias() {
        listaCategoriaviewModel.listarCategoria().observe(viewLifecycleOwner,
            Observer {
                binding.rvcategoria.adapter = CategoriaAdapter(it)
            })
    }

}