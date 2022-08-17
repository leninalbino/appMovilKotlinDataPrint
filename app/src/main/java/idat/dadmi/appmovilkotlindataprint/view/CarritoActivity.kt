package idat.dadmi.appmovilkotlindataprint.view

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
import idat.dadmi.appmovilkotlindataprint.view.adapter.CarritoAdapter
import idat.dadmi.appmovilkotlindataprint.viewmodel.CarritoViewModel

class CarritoActivity : AppCompatActivity() {

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

        listarItemsCarrito()
    }

    private fun listarItemsCarrito(){
        carritoViewModel.ListItemCart().observe(this, Observer{
            binding.rvlistacarrito.adapter = CarritoAdapter(it)
        })
    }

}