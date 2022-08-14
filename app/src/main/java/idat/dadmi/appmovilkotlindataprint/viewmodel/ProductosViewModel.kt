package idat.dadmi.appmovilkotlindataprint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dadmi.appmovilkotlindataprint.repository.ProdutoRepository
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseProductos

class ProductosViewModel:ViewModel() {
    private var repository=ProdutoRepository()
    fun listarProducto():LiveData<List<ResponseProductos>>{
        return repository.listarProductos()
    }

}