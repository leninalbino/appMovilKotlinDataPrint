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

    @POST("/rest/v1/usuarios/crearToken")
    //@Headers("No-Authentication: true")
    fun login (@Body requestLogin: RequestLogin):Call<ResponseLogin>

    @POST("/registrar")
    fun registro (@Body requestRegistro: RequestRegistro):Call<ResponseRegistro>

    @PUT("/validarCodigoVerificacion/{codigo}")
    fun validarCodigoVerificacion(@Path(value = "codigo") codigo:String):Call<ResponseCodigoVerificacion>

    //buscar producto por id para mostrar el detalle
    @GET("/p/{id}")
    fun findProductDetailById(@Path(value = "id") id:Int): Call<ResponseProductos>

    @POST("/rest/v1/carrito/agregarCarrito?)")
    fun agregarCarrito(@Query("caracteristica_id")caracteristica_id:Long,
                        @Query("cantidad")cantidad:Int):Call<ResponseAgregarCarrito>

    @GET("/rest/v1/carrito/")
    fun listarCarrito():Call<List<ResponseListaCarrito>>

    @PUT("/rest/v1/carrito/actualizarItemCarrito/{cantidad}/{idcarrito}")
    fun actualizarCarrito(@Path(value = "cantidad") cantidad: Int,
                          @Path(value="idcarrito") idcarrito:Long): Call<ResponseAgregarCarrito>

    @DELETE("/rest/v1/carrito/eliminarItemCarrito/{id}")
    fun  eliminarItemCarrito(@Path(value="id") id:Long):Call<ResponseAgregarCarrito>

    @POST("/rest/v1/ventas/create")
    fun pagarVenta():Call<ResponseVentas>

}