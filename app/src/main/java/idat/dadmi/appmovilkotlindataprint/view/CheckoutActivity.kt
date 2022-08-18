package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_checkout)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val responseListaCarrito = bundle?.getString("LISTA")
        println("checkout "+ responseListaCarrito)

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