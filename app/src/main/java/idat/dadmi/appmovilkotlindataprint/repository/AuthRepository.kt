package idat.dadmi.appmovilkotlindataprint.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import idat.dadmi.appmovilkotlindataprint.retrofit.DataPrintCliente
import idat.dadmi.appmovilkotlindataprint.retrofit.DataPrintService
import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestLogin
import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestRegistro
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseLogin
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    var loginResponse = MutableLiveData<ResponseLogin>()
    var registroResponse = MutableLiveData<ResponseRegistro>()

    fun autenticarUsuario(requestLogin: RequestLogin)
    :MutableLiveData<ResponseLogin>{
        val call:Call<ResponseLogin> = DataPrintCliente
            .retrofitService.login(requestLogin)
        call.enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                loginResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }

        })
        return loginResponse
    }
    fun registrarUsuario(requestRegistro: RequestRegistro)
    :MutableLiveData<ResponseRegistro>{
        val call:Call<ResponseRegistro> = DataPrintCliente
            .retrofitService.registro(requestRegistro)
        call.enqueue(object :Callback<ResponseRegistro>{
            override fun onResponse(
                call: Call<ResponseRegistro>,
                response: Response<ResponseRegistro>
            ) {
                registroResponse.value = response.body()
            }

            override fun onFailure(call: Call<ResponseRegistro>, t: Throwable) {
                Log.e("ErrorRegistro", t.message.toString())
            }

        })
        return registroResponse
    }
}