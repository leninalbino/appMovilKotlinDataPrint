package idat.dadmi.appmovilkotlindataprint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dadmi.appmovilkotlindataprint.repository.AuthRepository
import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestLogin
import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestRegistro
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseLogin
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseRegistro
import idat.dadmi.appmovilkotlindataprint.view.adapter.ProductoAdapter

class AuthViewModel:ViewModel() {
    var responseLogin:LiveData<ResponseLogin>
    var  responseRegistro:LiveData<ResponseRegistro>

    private  var repository = AuthRepository()
    init {
        responseLogin = repository.loginResponse
        responseRegistro = repository.registroResponse
    }

    fun autenticarUsuario(usuario:String,password:String){
        responseLogin = repository.autenticarUsuario(
            RequestLogin(usuario,password)
        )
    }

    fun registrarUsuario( nombre:String,
                          apellido:String,
                          correo: String,
                          clave:String,
                          direcc: String,
                          telefono:String,
                          dni:String){
        responseRegistro = repository.registrarUsuario(
            RequestRegistro(nombre, apellido,clave,correo,direcc,telefono, dni)
        )
    }
}