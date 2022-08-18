package idat.dadmi.appmovilkotlindataprint.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCarritoBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseListaCarrito
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje
import idat.dadmi.appmovilkotlindataprint.view.adapter.CarritoAdapter
import idat.dadmi.appmovilkotlindataprint.view.adapter.OnclickCarritoItem
import idat.dadmi.appmovilkotlindataprint.viewmodel.CarritoViewModel

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
        var lista:ResponseListaCarrito
        carritoViewModel.ListItemCart().observe(this, Observer{
            binding.rvlistacarrito.adapter = CarritoAdapter(this@CarritoActivity,it)
            println("lista del carrito"+it)
            lista= it.get(0)
            println("-----------"+lista)
            var mTotal :Double =0.0
            var mCantidad:Int=0
            it.forEach{

                mTotal += it.caracteristica.precioCaract
                println(it)

            }
            println(mTotal)
            binding.tvtotal.text= String.format("%.2f",mTotal).toString()

            binding.btnpagar.setOnClickListener {

                val intent =  Intent(this, CheckoutActivity::class.java)
                intent.putExtra("LISTA",lista.toString())
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