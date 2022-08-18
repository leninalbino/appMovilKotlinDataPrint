package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCheckoutBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseListaCarrito
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Usuario
import idat.dadmi.appmovilkotlindataprint.viewmodel.CarritoViewModel

class CheckoutActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var usuario: Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

            intent?.let {
                bundle ->
                usuario = bundle.getParcelableExtra("usuario")!!
            }
          // val usuario = intent.getParcelableExtra<Parcelable?>("usuario") as Usuario
            //println(usuario.apellido.toString())
            //val obj: Any? = intent.extras?.get("usuario")
            println(usuario.apellido)

        binding.btnpagocontraentrega.setOnClickListener(this)
        binding.ibtnpagoyape.setOnClickListener(this)
        binding.ibtnpaypal.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnpagocontraentrega -> pagoContraEntrega()
            R.id.ibtnpagoyape -> pagoConYape()
            R.id.ibtnpaypal -> pagoConPaypal()
        }
    }

    private fun pagoConPaypal() {
        TODO("Not yet implemented")
    }

    private fun pagoConYape() {
        TODO("Not yet implemented")
    }

    private fun pagoContraEntrega() {

        startActivity(Intent(this, PagoContraEntregaActivity::class.java))
    }
}