package idat.dadmi.appmovilkotlindataprint.retrofit


import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestLogin
import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestRegistro
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseCodigoVerificacion
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseLogin
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseProductos
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.http.*

interface DataPrintService {

    @GET("/")
    fun listarProductos (): Call<List<ResponseProductos>>

    @POST("/rest/v1/usuarios/crearToken")
    fun login (@Body requestLogin: RequestLogin):Call<ResponseLogin>

    @POST("/registrar")
    fun registro (@Body requestRegistro: RequestRegistro):Call<ResponseRegistro>

    @PUT("/validarCodigoVerificacion/{codigo}")
    fun validarCodigoVerificacion(@Path(value = "codigo") codigo:String):Call<ResponseCodigoVerificacion>

}