package idat.dadmi.appmovilkotlindataprint.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import idat.dadmi.appmovilkotlindataprint.retrofit.DataPrintCliente
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseProductos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoRepository {
    var responseProducto= MutableLiveData<List<ResponseProductos>>()

    fun listarProductos() :MutableLiveData<List<ResponseProductos>> {
        val call: Call<List<ResponseProductos>> = DataPrintCliente("")
            .retrofitService.listarProductos()
        call.enqueue(object : Callback<List<ResponseProductos>> {
            override fun onResponse
                        (call: Call<List<ResponseProductos>>,
                         response: Response<List<ResponseProductos>>) {
                responseProducto.value = response.body()
            }
            override fun onFailure(call: Call<List<ResponseProductos>>, t: Throwable) {
                Log.e("ErrorProducto", t.message.toString())
            }
        })
    return responseProducto
    }   



}