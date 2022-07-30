package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import idat.dadmi.appmovilkotlindataprint.CaracterizticasActivity
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityLoginBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseLogin
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje
import idat.dadmi.appmovilkotlindataprint.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regresarHome=findViewById<ImageView>(R.id.regresar)
        regresarHome.setOnClickListener{val intent =  Intent(this, CaracterizticasActivity::class.java)
            startActivity(intent)
        }
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)

        binding.btnregistrar.setOnClickListener(this)
        binding.btnlogin.setOnClickListener(this)
        authViewModel.responseLogin.observe(this, Observer {
            obtenerDatosLogin(it!!)
        })

    }

    private fun obtenerDatosLogin(responseLogin: ResponseLogin) {
        if(responseLogin.token != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }else{
            AppMensaje.enviarMensaje(binding.root, "Usuario o Password Incorrecto", TipoMensaje.ERROR)
        }
        binding.btnlogin.isEnabled = true
        binding.btnregistrar.isEnabled = true

    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnlogin -> autenticarUsuario()
            R.id.btnregistrar -> irRegistroUsuario()
        }
    }

    private fun irRegistroUsuario() {
        startActivity(Intent(applicationContext,RegistroActivity::class.java))
    }

    private fun autenticarUsuario() {
        binding.btnlogin.isEnabled = false
        binding.btnregistrar.isEnabled = false

        var okLogin = true

        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okLogin= false
        }else if(binding.etpassword.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okLogin= false
        }
        if(okLogin){
            authViewModel.autenticarUsuario(binding.etusuario.text.toString(),
                                            binding.etpassword.text.toString())
        }else{
            binding.btnlogin.isEnabled = true
            AppMensaje.enviarMensaje(binding.root,
                                    "Usuario o Password Incorrecto", TipoMensaje.ERROR)
        }
    }


}

