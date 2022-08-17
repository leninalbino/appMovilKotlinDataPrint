package idat.dadmi.appmovilkotlindataprint.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.FragmentDetalleProductoBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.model.Producto
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Caracteristica
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito
import idat.dadmi.appmovilkotlindataprint.utilitarios.*
import idat.dadmi.appmovilkotlindataprint.view.CarritoActivity
import idat.dadmi.appmovilkotlindataprint.view.LoginActivity
import idat.dadmi.appmovilkotlindataprint.viewmodel.CarritoViewModel


class DetalleProductoFragment : Fragment(R.layout.fragment_detalle_producto),View.OnClickListener
{

    private var _binding: FragmentDetalleProductoBinding?=null
    private val binding get() = _binding!!

    private lateinit var producto:Producto // class Producto
    private lateinit var carritoViewModel: CarritoViewModel

    private val caractList: ArrayList<Caracteristica> = ArrayList()

    //private lateinit var detalleProductoViewModel:DetalleProductoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            producto= bundle.getParcelable("producto")!!
        }
        carritoViewModel = ViewModelProvider(this).get(CarritoViewModel::class.java)
        carritoViewModel.responseAgregarCarrito.observe(this, Observer {
            obtenerRespuestaAgregarCarrito(it!!)
            //Toast.makeText(context, "Mensaje: $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun obtenerRespuestaAgregarCarrito(responseAgregarCarrito: ResponseAgregarCarrito) {
        AppMensaje.enviarMensaje(binding.root,
            ""+responseAgregarCarrito.mensaje, TipoMensaje.EXITO)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleProductoBinding.inflate(
            inflater,
            container,
            false)

        //Carga la Imagen que llega del Home
        Glide.with(requireContext())
            .load(producto.imageProp)
            .into(binding.ivproducto)

        //añadimos este metodo para que carge el spinner
        setUpSpinner()

        //Para el funcionamiento del boton
        binding.btnagregarcarrito.setOnClickListener(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Cargamos los txt con los datos que obtenemos de Home
        binding.tvnombreproducto.text = producto.nombrePro
        binding.tvidproducto.text=producto.idProductoPro.toString()

    }
    //Metodo para enviar datos a un Activity.. No se esta utlizando por ahora
    fun sendData(){
        binding.btnagregarcarrito.setOnClickListener{
            if(obtenerToken()!=""){
                val carrito = Intent  (activity, CarritoActivity::class.java)

                //carrito.putExtra("SENDER_KEY", "FragmentDetalleProductoBinding")
                carrito.putExtra("CANTIDAD_KEY", binding.etcantidad.text.toString())
                println("carritoooo "+ carrito)
                startActivity(carrito)
            }
            println("Logeateeeeeeeeeeeeeee")
            println("tokeneee " + obtenerToken())
        }


    }
    // Metodo de Prueba para obtener el token guardado.. Tambien no esta en uso en el app
    fun obtenerToken():String?{
        return SharedPreferencesManager()
            .getSomeStringValue(Constantes().PREF_TOKEN)
    }

    // variable para almacenar el id selecionado del Spinner
    var idCaracteristica:Long=0
    // metodo para Mostrar los datos en el Spinner
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
                idCaracteristica = producto.caracteristicas[position].idCaracteristica
                val  precio = producto.caracteristicas[position].precioCaract
                val nomCategoria = producto.categoria.nombreCate
                binding.tvprecio.text = precio.toString()
                binding.tvcate.text = nomCategoria
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun agregarItemCarrito(){
        var okCantidad=true
        if(Metodos.obtenerToken() != ""){


            if (binding.etcantidad.text.toString().trim().isEmpty()){
                binding.etcantidad.isFocusableInTouchMode = true
                binding.etcantidad.requestFocus()
                okCantidad = false
            }
            if (okCantidad){
                carritoViewModel.addItemCart(
                    idCaracteristica,binding.etcantidad.text.toString().toInt())
            }else{

                AppMensaje.enviarMensaje(binding.root,
                    "Es Requerido la cantidad", TipoMensaje.ERROR)

            }
        }else{
            if (binding.etcantidad.text.toString().trim().isEmpty()){
                binding.etcantidad.isFocusableInTouchMode = true
                binding.etcantidad.requestFocus()
                okCantidad = false
            }else{
                startActivity(Intent(context,LoginActivity::class.java))
            }
            AppMensaje.enviarMensaje(binding.root,
                "Es Requerido la cantidad", TipoMensaje.ERROR)
        }

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnagregarcarrito ->agregarItemCarrito()
        }
    }

}

