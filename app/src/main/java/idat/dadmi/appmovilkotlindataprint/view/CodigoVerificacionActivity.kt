package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCodigoVerificacionBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseCodigoVerificacion
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje
import idat.dadmi.appmovilkotlindataprint.viewmodel.AuthViewModel
import idat.dadmi.appmovilkotlindataprint.viewmodel.CodigoVerificacionViewModel

class CodigoVerificacionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCodigoVerificacionBinding
    private lateinit var codigoVerificacionViewModel: CodigoVerificacionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodigoVerificacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnvalidar.setOnClickListener(this)

        codigoVerificacionViewModel=ViewModelProvider(this).get(CodigoVerificacionViewModel::class.java)
        codigoVerificacionViewModel.responseCodigoVerificacion.observe(this, Observer { codigo ->
            obtenerResultadoValidado(codigo)
        })

    }
    private fun obtenerResultadoValidado(respuestaCodigo:ResponseCodigoVerificacion){
        if(respuestaCodigo.mensaje == "Su cuenta ha sido verificada correctamente"){
            setearControles()
            AppMensaje.enviarMensaje(binding.root,
            respuestaCodigo.mensaje,TipoMensaje.EXITO)
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }else{
            AppMensaje.enviarMensaje(binding.root,
            respuestaCodigo.mensaje, TipoMensaje.ERROR)
        }
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnvalidar -> registrarCodigo()
        }
    }
    private fun registrarCodigo(){

        if (validarCodigo()){
            codigoVerificacionViewModel.verificarCodigo(binding.etcodigoverificador.text.toString())
        }
    }

    private fun validarCodigo():Boolean{
        var respuesta = true
        var mensaje = ""
        when {
            binding.etcodigoverificador.text.toString().trim().isEmpty() -> {
                binding.etcodigoverificador.isFocusableInTouchMode = true
                binding.etcodigoverificador.requestFocus()
                mensaje = "Ingrese su codigo enviado a su correo"
                respuesta = false
            }
        }
        if (!respuesta) AppMensaje.enviarMensaje(binding.root, mensaje,
            TipoMensaje.ERROR)
        return respuesta
    }

    private fun setearControles(){
        binding.etcodigoverificador.setText("")
    }
}