package idat.dadmi.appmovilkotlindataprint.utilitarios

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

//class AuthInterceptor() :Interceptor{
    class AuthInterceptor(var token: String) :Interceptor{
    private val sessionMananger = SharedPreferencesManager()
    //val token:String =Constantes().PREF_TOKEN
    override fun intercept(chain: Interceptor.Chain): Response {


        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
           .build()
        Log.w("accessToken", "AuthInterceptor token: $token")
        return chain.proceed(newRequest)
       // val requestBuilder = chain.request().newBuilder() //sessionMananger.getSomeStringValue(token)?.let {
         //  requestBuilder.
         // addHeader("Authorization", "Bearer $it")
        // Log.w("accessToken", "AuthInterceptor token: $it")

        //}

       // return chain.proceed(requestBuilder.build())
    }
}