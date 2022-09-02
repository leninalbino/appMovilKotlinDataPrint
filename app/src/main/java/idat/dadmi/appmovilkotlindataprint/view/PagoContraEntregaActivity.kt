package idat.dadmi.appmovilkotlindataprint.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityPagoContraEntregaBinding

class PagoContraEntregaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPagoContraEntregaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagoContraEntregaBinding.inflate(layoutInflater)
                        setContentView(binding.root)
    }
}