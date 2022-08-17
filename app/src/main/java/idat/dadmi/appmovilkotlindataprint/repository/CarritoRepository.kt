package idat.dadmi.appmovilkotlindataprint.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import idat.dadmi.appmovilkotlindataprint.retrofit.DataPrintCliente
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseListaCarrito
import idat.dadmi.appmovilkotlindataprint.utilitarios.Metodos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarritoRepository {
    var agregarCarritoResponse = MutableLiveData <ResponseAgregarCarrito>()
    var listarCarrritoResponse = MutableLiveData<List<ResponseListaCarrito>>()

    fun addItem(caracteristica_id:Long,  cantidad:Int):MutableLiveData<ResponseAgregarCarrito>{
        if (Metodos.obtenerToken() != null){
            val call:Call<ResponseAgregarCarrito> = DataPrintCliente(Metodos.obtenerToken()!!)
                .retrofitService.agregarCarrito(caracteristica_id,cantidad)
            call.enqueue(object  : Callback<ResponseAgregarCarrito>{
                override fun onResponse(
                    call: Call<ResponseAgregarCarrito>,
                    response: Response<ResponseAgregarCarrito>
                ) {
                    agregarCarritoResponse.value = response.body()
                }

                override fun onFailure(call: Call<ResponseAgregarCarrito>, t: Throwable) {
                    Log.e("ErrorAgregarCarrito", t.message.toString())
                }

            })
        }

        return agregarCarritoResponse
    }
    fun listItemCart():MutableLiveData <List<ResponseListaCarrito>>{
        if(Metodos.obtenerToken() != ""){
            val call : Call<List<ResponseListaCarrito>> = DataPrintCliente(Metodos.obtenerToken()!!)
                .retrofitService.listarCarrito()
            call.enqueue(object : Callback <List<ResponseListaCarrito>>{
                override fun onResponse(
                    call: Call<List<ResponseListaCarrito>>,
                    response: Response<List<ResponseListaCarrito>>
                ) {
                    listarCarrritoResponse.value = response.body()
                }

                override fun onFailure(call: Call<List<ResponseListaCarrito>>, t: Throwable) {
                    Log.e("ErrorListarCarrito", t.message.toString())
                }
            })
        }

    return listarCarrritoResponse
    }

    fun updateAmountItem(cantidad:Int, idCarrito:Long):MutableLiveData<ResponseAgregarCarrito>{
        val call:Call<ResponseAgregarCarrito> = DataPrintCliente(Metodos.obtenerToken()!!)
            .retrofitService.actualizarCarrito(cantidad,idCarrito)
        call.enqueue(object  : Callback<ResponseAgregarCarrito>{
            override fun onResponse(
                call: Call<ResponseAgregarCarrito>,
                response: Response<ResponseAgregarCarrito>
            ) {
                agregarCarritoResponse.value = response.body()
            }

            override fun onFailure(call: Call<ResponseAgregarCarrito>, t: Throwable) {
                Log.e("ErrorUpdateAmountItem", t.message.toString())
            }
        })
        return agregarCarritoResponse
    }

    fun eliminarItemCarrito(id:Long):MutableLiveData<ResponseAgregarCarrito>{

        if (Metodos.obtenerToken()!=""){
            println(""+Metodos.obtenerToken())
            val call:Call<ResponseAgregarCarrito> = DataPrintCliente(Metodos.obtenerToken()!!)
                .retrofitService.eliminarItemCarrito(id)
            call.enqueue(object  : Callback<ResponseAgregarCarrito>{
                override fun onResponse(
                    call: Call<ResponseAgregarCarrito>,
                    response: Response<ResponseAgregarCarrito>
                ) {
                    agregarCarritoResponse.value = response.body()
                }

                override fun onFailure(call: Call<ResponseAgregarCarrito>, t: Throwable) {
                    Log.e("ErrorEliminartItemCarrito", t.message.toString())
                }
            })
        }


        return agregarCarritoResponse
    }
}