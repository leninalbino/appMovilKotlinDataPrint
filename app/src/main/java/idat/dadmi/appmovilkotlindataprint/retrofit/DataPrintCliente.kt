package idat.dadmi.appmovilkotlindataprint.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var url= "http://8086:"
object DataPrintCliente {
    private  var okHttpClient=OkHttpClient.Builder()
        .connectTimeout(1,TimeUnit.MINUTES)
        .readTimeout(30,TimeUnit.MINUTES)
        .writeTimeout(15,TimeUnit.MINUTES)
        .build()

    private fun buildRetrofit() =Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
   // el Lazy vota error
   // var retrofitService:DataPrintService by Lazy{
     //   buildRetrofit().create(DataPrintService::class.java)
    //}
}