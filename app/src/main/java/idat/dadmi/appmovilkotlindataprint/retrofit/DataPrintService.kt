package idat.dadmi.appmovilkotlindataprint.retrofit


import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestLogin
import idat.dadmi.appmovilkotlindataprint.retrofit.request.RequestRegistro
import idat.dadmi.appmovilkotlindataprint.retrofit.response.*
import retrofit2.Call
import retrofit2.http.*

interface DataPrintService {

    @GET("/")
    fun listarProductos (): Call<List<ResponseProductos>>

    //@GET("/")
    //fun listarCategorias (): Call<List<ResponseCategoria>>
    //@FormUrlEncoded
    //@Headers("Acept:aplication/json")
    @POST("/rest/v1/usuarios/crearToken")
    fun login (@Body requestLogin: RequestLogin):Call<ResponseLogin>

    @POST("/registrar")
    fun registro (@Body requestRegistro: RequestRegistro):Call<ResponseRegistro>

    @PUT("/validarCodigoVerificacion/{codigo}")
    fun validarCodigoVerificacion(@Path(value = "codigo") codigo:String):Call<ResponseCodigoVerificacion>

    //buscar producto por id para mostrar el detalle
    @GET("/p/{id}")
    fun findProductDetailById(@Path(value = "id") id:Int): Call<ResponseProductos>

}