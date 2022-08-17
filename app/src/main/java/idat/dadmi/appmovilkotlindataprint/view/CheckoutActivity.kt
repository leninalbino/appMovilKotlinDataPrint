package idat.dadmi.appmovilkotlindataprint.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_checkout)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}