package idat.dadmi.appmovilkotlindataprint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dadmi.appmovilkotlindataprint.repository.CodigoVerificacionRepository
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseCodigoVerificacion

class CodigoVerificacionViewModel:ViewModel() {
    var responseCodigoVerificacion: LiveData<ResponseCodigoVerificacion>

    private var repository =  CodigoVerificacionRepository()
    init {
        responseCodigoVerificacion=repository.responseCodigoVerificacion
    }

    fun verificarCodigo(codigo:String){
        responseCodigoVerificacion = repository.verificcarCodigo(codigo)
    }
}