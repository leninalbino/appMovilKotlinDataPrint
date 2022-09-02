package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCarritoBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.model.Usuario
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje
import idat.dadmi.appmovilkotlindataprint.view.adapter.CarritoAdapter
import idat.dadmi.appmovilkotlindataprint.view.adapter.OnclickCarritoItem
import idat.dadmi.appmovilkotlindataprint.viewmodel.CarritoViewModel
import java.io.Serializable

class CarritoActivity : AppCompatActivity(), OnclickCarritoItem {

    private lateinit var binding: ActivityCarritoBinding
    private lateinit var carritoViewModel: CarritoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
                            setContentView(binding.root)

        binding.rvlistacarrito.layoutManager = LinearLayoutManager(this)

        // <- (flechita) para regresar al home
        val regresarHome=findViewById<ImageView>(R.id.regresar)
        regresarHome.setOnClickListener{val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        carritoViewModel = ViewModelProvider(this)
                                    .get(CarritoViewModel::class.java)
        carritoViewModel.responseAgregarCarrito.observe(this, Observer{
            obtenerRespuestaMensaje(it!!)
        })

        listarItemsCarrito()
    }

    private fun obtenerRespuestaMensaje(it: ResponseAgregarCarrito) {
        AppMensaje.enviarMensaje(binding.root, it.mensaje, TipoMensaje.EXITO)
    }

    private fun listarItemsCarrito(){

        carritoViewModel.ListItemCart().observe(this, Observer{
            binding.rvlistacarrito.adapter = CarritoAdapter(this@CarritoActivity,it)

            var lista = it
            var mTotal :Double =0.0
            var mCantidad:Int=0
            it.forEach{it ->
                mTotal += it.caracteristica.precioCaract

            }
            binding.tvtotal.text= String.format("%.2f",mTotal).toString()

            var idusuario=  it[0].usuario.idusuarios
            var nombre= it[0].usuario.nombre
            var apellido=it[0].usuario.apellido
            var dni=it[0].usuario.dni
            var telefono=it[0].usuario.telefono
            var direccion=it[0].usuario.direcc
            var correo=it[0].usuario.correo

            val usuario= Usuario(
                idusuario,
                nombre.toString(),
                apellido.toString(),
                dni.toString(),
                telefono.toString(),
                direccion.toString(),
                correo
            )
            binding.btnpagar.setOnClickListener {

                val intent =  Intent(this, CheckoutActivity::class.java)
                startActivity(intent)

            }
        })

    }

    override fun OnDeleteItem(id: Long) {
        carritoViewModel.deleteItemCart(id)
    }

    override fun OnUpdateAmount(cantidad: Int, id: Long) {
        if(cantidad >0 || cantidad !=null){
            carritoViewModel.updateAmountItem(cantidad, id)
        }

    }

}