package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import idat.dadmi.appmovilkotlindataprint.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val regresarHome=findViewById<ImageView>(R.id.regresar)
        regresarHome.setOnClickListener{val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

