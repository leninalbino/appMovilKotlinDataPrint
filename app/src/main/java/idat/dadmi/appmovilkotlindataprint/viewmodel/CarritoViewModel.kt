package idat.dadmi.appmovilkotlindataprint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dadmi.appmovilkotlindataprint.repository.CarritoRepository
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseListaCarrito

class CarritoViewModel: ViewModel() {

    var responseAgregarCarrito:LiveData<ResponseAgregarCarrito>
    var responseListaCarrito:LiveData<List<ResponseListaCarrito>>

    private var repository = CarritoRepository()

    init {
        responseAgregarCarrito = repository.agregarCarritoResponse
        responseListaCarrito = repository.listarCarrritoResponse
    }

    fun addItemCart(caracteristica_id:Long,
                    cantidad:Int){
        responseAgregarCarrito = repository.addItem(caracteristica_id ,cantidad)
    }

    fun ListItemCart():LiveData<List<ResponseListaCarrito>>{
        return repository.listItemCart()
    }
    fun updateAmountItem(cantidad: Int, idCarrito:Long){
        responseAgregarCarrito = repository.updateAmountItem(cantidad,idCarrito)
    }

    fun deleteItemCart(id:Long){
        responseAgregarCarrito = repository.eliminarItemCarrito(id)
    }
}