package idat.dadmi.appmovilkotlindataprint.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityPagosStripeBinding

class PagosStripeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPagosStripeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagosStripeBinding.inflate(layoutInflater)
                    setContentView(binding.root)
    }
}