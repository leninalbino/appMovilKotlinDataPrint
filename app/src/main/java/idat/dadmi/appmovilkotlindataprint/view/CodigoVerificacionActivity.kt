package idat.dadmi.appmovilkotlindataprint.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCodigoVerificacionBinding
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityRegistroBinding

class CodigoVerificacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCodigoVerificacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodigoVerificacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}