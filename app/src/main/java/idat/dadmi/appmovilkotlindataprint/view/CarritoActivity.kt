package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCarritoBinding

class CarritoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_carrito)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regresarHome=findViewById<ImageView>(R.id.regresar)
        regresarHome.setOnClickListener{val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val bundle : Bundle? = intent.extras
        bundle.let {
            it?.apply {
                val cantidad : String? = getString("CANTIDAD_KEY")
                binding.tvcantidad.text=cantidad

                println("aquiiiiiiiiiiiii "+cantidad)
            }
        }
    }
    fun receiveData(){
        var i = Intent(intent)

    }
}