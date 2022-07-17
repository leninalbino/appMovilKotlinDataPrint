package idat.dadmi.appmovilkotlindataprint.retrofit

import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseProductos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface DataPrintService {

    @GET("/")
    fun listarProductos (): Call<List<ResponseProductos>>

}