package idat.dadmi.appmovilkotlindataprint.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.FragmentDetalleProductoBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.model.Producto
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Caracteristica
import idat.dadmi.appmovilkotlindataprint.view.CarritoActivity


class DetalleProductoFragment : Fragment(R.layout.fragment_detalle_producto) {

    private var _binding: FragmentDetalleProductoBinding?=null
    private val binding get() = _binding!!

    private lateinit var producto:Producto // class Producto

    private val caractList: ArrayList<Caracteristica> = ArrayList()

    //private lateinit var detalleProductoViewModel:DetalleProductoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            producto= bundle.getParcelable("producto")!!
        }

        //detalleProductoViewModel = ViewModelProvider(this).get(DetalleProductoViewModel::class.java)
        //detalleProductoViewModel
        // .findDetalleProductoById(producto.idProductoPro.toInt())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleProductoBinding.inflate(
            inflater,
            container,
            false)


        Glide.with(requireContext())
            .load(producto.imageProp)
            .into(binding.ivproducto)

        //detalleProductoViewModel= ViewModelProvider(requireActivity())
        //    .get(DetalleProductoViewModel::class.java)
        setUpSpinner()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvnombreproducto.text = producto.nombrePro
        binding.tvidproducto.text=producto.idProductoPro.toString()

        //binding.btnagregarcarrito.setOnClickListener{view : View ->
        sendData()
        //}

    }
    fun sendData(){
        binding.btnagregarcarrito.setOnClickListener{
            val carrito = Intent  (activity, CarritoActivity::class.java)

            //carrito.putExtra("SENDER_KEY", "FragmentDetalleProductoBinding")
            carrito.putExtra("CANTIDAD_KEY", binding.etcantidad.text.toString())
            println("carritoooo "+ carrito)
            startActivity(carrito)
        }


    }
    private fun setUpSpinner(){
        val listado: MutableList<String> = ArrayList()
        for (i in 0 until producto.caracteristicas.size){
            listado.add(producto.caracteristicas.get(i).descriCaract)
        }
        val adaptador= activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, listado) }
        binding.spinner.adapter=adaptador
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val  precio = producto.caracteristicas[position].precioCaract
                val nomCategoria = producto.categoria.nombreCate
                binding.tvprecio.text = precio.toString()
                binding.tvcate.text = nomCategoria
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    }

}

