package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import idat.dadmi.appmovilkotlindataprint.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME :Long =2500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },SPLASH_TIME)
    }
}