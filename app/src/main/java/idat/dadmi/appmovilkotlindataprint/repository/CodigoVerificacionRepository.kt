package idat.dadmi.appmovilkotlindataprint.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import idat.dadmi.appmovilkotlindataprint.retrofit.DataPrintCliente
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseCodigoVerificacion
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class CodigoVerificacionRepository {
    var responseCodigoVerificacion=MutableLiveData<ResponseCodigoVerificacion>()

    fun verificcarCodigo(codigo:String):MutableLiveData<ResponseCodigoVerificacion>{
        val call: Call<ResponseCodigoVerificacion> = DataPrintCliente
            .retrofitService.validarCodigoVerificacion(codigo)
        call.enqueue(object :Callback<ResponseCodigoVerificacion>{
            override fun onResponse(
                call: Call<ResponseCodigoVerificacion>,
                response: Response<ResponseCodigoVerificacion>
            ) {
                responseCodigoVerificacion.value=response.body()

            }

            override fun onFailure(call: Call<ResponseCodigoVerificacion>, t: Throwable) {
                Log.e("ErrorCodigoVerificador", t.message.toString())
            }
        })
        return responseCodigoVerificacion
    }
}