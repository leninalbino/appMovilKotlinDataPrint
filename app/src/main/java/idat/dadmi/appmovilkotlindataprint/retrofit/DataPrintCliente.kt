package idat.dadmi.appmovilkotlindataprint.retrofit

import idat.dadmi.appmovilkotlindataprint.utilitarios.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataPrintCliente {

    private  var okHttpClient=OkHttpClient.Builder()
        .connectTimeout(1,TimeUnit.MINUTES) // Tiempo de espera de 1 minuto para la conexion
        .readTimeout(30,TimeUnit.MINUTES) // Tiempo de espera leer informacion de una api rest
        .writeTimeout(15,TimeUnit.MINUTES) // Tiempo de espera para Escribir una peticion
        .build()

    private fun buildRetrofit() =Retrofit.Builder()
        .baseUrl(Constantes().Data_Print_URL)// Url Base donde esta la conexion
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())//convierte el json del servidor al
        .build()

    val retrofitService:DataPrintService by lazy{ // by lazy solo se inicializa la variable al moemento de llamar a webservice
        buildRetrofit().create(DataPrintService::class.java)
    }
}