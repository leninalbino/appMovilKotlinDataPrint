package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.core.util.PatternsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import idat.dadmi.appmovilkotlindataprint.CaracterizticasActivity
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityLoginBinding
import idat.dadmi.appmovilkotlindataprint.db.entity.UsuarioEntity
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseLogin
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.Constantes
import idat.dadmi.appmovilkotlindataprint.utilitarios.SharedPreferencesManager
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje
import idat.dadmi.appmovilkotlindataprint.viewmodel.AuthViewModel
import idat.dadmi.appmovilkotlindataprint.viewmodel.UsuarioViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regresarHome=findViewById<ImageView>(R.id.regresar)
        regresarHome.setOnClickListener{val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)

        usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)

        // validacion si los datos estan registrados en la bd en memoria
        if (validarCheckRecordarDatos()){
            binding.chkrecordar.isChecked = true
            binding.etusuario.isEnabled=false
            binding.etpassword.isEnabled=false
            binding.chkrecordar.text = "Quitar el check para ingresar con otro usuario"
            usuarioViewModel.obtener()
                .observe(this, Observer {
                    usuario ->
                    usuario?.let {
                        binding.etusuario.setText(usuario.user)
                        binding.etpassword.setText(usuario.password)
                    }
                    println("aquiiiii "+usuario)
                }
                )
        }else{
            usuarioViewModel.eliminartodo()
        }

        binding.btnregistrar.setOnClickListener(this)
        binding.btnlogin.setOnClickListener(this)

        authViewModel.responseLogin.observe(this, Observer {
            obtenerDatosLogin(it!!)
        })

    }

    fun validarCheckRecordarDatos():Boolean{
        return SharedPreferencesManager()
                        .getSomeBooleanValue(Constantes()
                            .PREF_RECORDAR)
    }
    fun validarRecordarToken():String{
        return SharedPreferencesManager()
            .getSomeStringValue(Constantes().PREF_TOKEN)
    }
    private fun obtenerDatosLogin(responseLogin: ResponseLogin) {
        val usuario = binding.etusuario.text.toString()
        if(responseLogin.user == usuario){
            println("Userrr"+responseLogin.user)
            println("token"+responseLogin.token)
            val usuarioEntity = UsuarioEntity(
                1,
                responseLogin.token,
                responseLogin.user,

                binding.etpassword.text.toString()
            )
            if(validarCheckRecordarDatos()){
                usuarioViewModel.actualizar(usuarioEntity)
            }else{
                usuarioViewModel.insertar(usuarioEntity)
                if (binding.chkrecordar.isChecked){
                    SharedPreferencesManager().setSomeBooleanValue(Constantes().PREF_RECORDAR,true)
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            AppMensaje.enviarMensaje(binding.root, "Bienvenido Estimado Usuario: "+ usuario , TipoMensaje.EXITO)
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
            R.id.chkrecordar -> setearControlesCheckRecordar(p0)
        }
    }

    private fun setearControlesCheckRecordar(vista: View) {
        if (vista is CheckBox){
            val checked = vista.isChecked
            if (!checked){
                if (validarCheckRecordarDatos()){
                    SharedPreferencesManager().deletePreference(Constantes().PREF_RECORDAR)
                    usuarioViewModel.eliminartodo()
                    binding.etusuario.isEnabled=true
                    binding.etpassword.isEnabled=true
                    binding.chkrecordar.text = getString(R.string.valchkguardardatos)
                }
            }
        }
    }

    private fun irRegistroUsuario() {
        startActivity(Intent(applicationContext,RegistroActivity::class.java))
    }

    private fun autenticarUsuario() {
        binding.btnlogin.isEnabled = false
        binding.btnregistrar.isEnabled = false
        var okLogin = true

        var usuario = binding.etusuario.text.toString()
        var password =binding.etpassword.text.toString()
     if(usuario.trim().isEmpty()){
         binding.etusuario.isFocusableInTouchMode = true
         binding.etusuario.requestFocus()
         okLogin= false
     }
      else if( !PatternsCompat.EMAIL_ADDRESS.matcher(usuario).matches()) {
          binding.etusuario.isFocusableInTouchMode = true
          binding.etusuario.requestFocus()
          okLogin= false
      }
     else if (password.trim().isEmpty()){
         binding.etusuario.isFocusableInTouchMode = true
         binding.etusuario.requestFocus()
         okLogin= false
     }
    if(okLogin ){
            authViewModel.autenticarUsuario(binding.etusuario.text.toString(),
                binding.etpassword.text.toString())
       /* AppMensaje.enviarMensaje(binding.root,
            "BienVenido" + usuario, TipoMensaje.EXITO)*/
        }else{

        binding.btnlogin.isEnabled = true
            AppMensaje.enviarMensaje(binding.root,
                "Usuario o Password Incorrecto", TipoMensaje.ERROR)
        }
     /*   if(binding.etusuario.text.toString().trim().isEmpty()){
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
        }*/
    }


}

