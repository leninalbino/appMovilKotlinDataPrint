package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityRegistroBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseRegistro
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje
import idat.dadmi.appmovilkotlindataprint.viewmodel.AuthViewModel
import org.intellij.lang.annotations.Pattern


class RegistroActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnirlogin.setOnClickListener(this)
        binding.btnregistrarme.setOnClickListener(this)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        authViewModel.responseRegistro.observe(this, Observer {respuestaRegistro ->
            obtenerResultadoRegistro(respuestaRegistro)

        })
    }

    private fun obtenerResultadoRegistro(respuestaRegistro: ResponseRegistro) {
        if(respuestaRegistro.mensaje== "Se Registro el usuario correctamente"){
            setearControles()
            AppMensaje.enviarMensaje(binding.root,
                respuestaRegistro.mensaje, TipoMensaje.EXITO)
            startActivity(Intent(applicationContext, CodigoVerificacionActivity::class.java))
        }else{
            AppMensaje.enviarMensaje(binding.root,
                respuestaRegistro.mensaje, TipoMensaje.ERROR)
        }

        binding.btnregistrarme.isEnabled = true
        binding.btnirlogin.isEnabled = true

    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnregistrarme -> registrarUsuario()
            R.id.btnirlogin -> startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }

    private fun registrarUsuario() {
        binding.btnregistrarme.isEnabled = false
        binding.btnirlogin.isEnabled = false
        if(validadFormulario() ){
            authViewModel.registrarUsuario(
                binding.etnomusuario.text.toString(),
                binding.etapeusuario.text.toString(),
                binding.etemailusuario.text.toString(),
                binding.etpassreg.text.toString(),
                binding.etdireccion.text.toString(),
                binding.etcelusuario.text.toString(),
                binding.etusudni.text.toString(),

            )
        }else{
            binding.btnregistrarme.isEnabled = true
            binding.btnirlogin.isEnabled = true
        }
    }

    private fun validadFormulario(): Boolean {
        var respuesta = true
        var mensaje = ""
        when{
            binding.etnomusuario.text.toString().trim().isEmpty() ->{
                binding.etnomusuario.isFocusableInTouchMode = true
                binding.etnomusuario.requestFocus()
                mensaje = "Ingrese su nombre"
                respuesta = false
            }
            binding.etapeusuario.text.toString().trim().isEmpty() ->{
                binding.etapeusuario.isFocusableInTouchMode = true
                binding.etapeusuario.requestFocus()
                mensaje = "Ingrese su apellido"
                respuesta = false
            }
            binding.etemailusuario.text.toString().trim().isEmpty() ->{
                binding.etemailusuario.isFocusableInTouchMode = true
                binding.etemailusuario.requestFocus()
                mensaje = "Ingrese su email"
                respuesta = false
            }
            binding.etcelusuario.text.toString().trim().isEmpty() ->{
                binding.etcelusuario.isFocusableInTouchMode = true
                binding.etcelusuario.requestFocus()
                mensaje = "Ingrese su celular"
                respuesta = false
            }
            binding.etusudni.text.toString().trim().isEmpty() ->{
                binding.etusudni.isFocusableInTouchMode = true
                binding.etusudni.requestFocus()
                mensaje = "Ingrese su DNI"
                respuesta = false
            }
            binding.etpassreg.text.toString().trim().isEmpty() ->{
                binding.etpassreg.isFocusableInTouchMode = true
                binding.etpassreg.requestFocus()
                mensaje = "Ingrese su password"
                respuesta = false
            }
            binding.etdireccion.text.toString().trim().isEmpty() ->{
                binding.etdireccion.isFocusableInTouchMode = true
                binding.etdireccion.requestFocus()
                mensaje = "Ingrese su direccion"
                respuesta = false

            }
            //binding.etdireccion.text.toString().trim().isNotEmpty() ->{
             //   if(binding.etpassreg.text.toString() != binding.etdireccion.text.toString()){
            //        binding.etdireccion.isFocusableInTouchMode = true
            //        binding.etdireccion.requestFocus()
            //        mensaje = "Direccion invalido"
            //        respuesta = false
            //    }
            //}
        }
        if (!respuesta) AppMensaje.enviarMensaje(binding.root, mensaje,
            TipoMensaje.ERROR)
        return respuesta
    }
    private fun setearControles(){
        binding.etnomusuario.setText("")
        binding.etapeusuario.setText("")
        binding.etemailusuario.setText("")
        binding.etcelusuario.setText("")
        binding.etusudni.setText("")
        binding.etpassreg.setText("")
        binding.etdireccion.setText("")
    }
}