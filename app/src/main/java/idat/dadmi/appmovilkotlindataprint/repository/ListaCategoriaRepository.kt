package idat.dadmi.appmovilkotlindataprint.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import idat.dadmi.appmovilkotlindataprint.retrofit.DataPrintCliente
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Categoria
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaCategoriaRepository {
    var responselistacategoria = MutableLiveData<List<Categoria>>()

    fun listarCategoria(): MutableLiveData<List<Categoria>> {
        val call: Call<List<Categoria>> = DataPrintCliente("")
            .retrofitService.listarCategoria()
        call.enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(
                call: Call<List<Categoria>>,
                response: Response<List<Categoria>>
            ) {
                responselistacategoria.value = response.body();
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                Log.e("ErrorListaCategoria", t.message.toString())
            }
        })
        return responselistacategoria
    }
}