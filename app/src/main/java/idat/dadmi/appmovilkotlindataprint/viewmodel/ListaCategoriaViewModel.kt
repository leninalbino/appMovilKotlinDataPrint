package idat.dadmi.appmovilkotlindataprint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dadmi.appmovilkotlindataprint.repository.ListaCategoriaRepository
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Categoria

class ListaCategoriaViewModel:ViewModel() {
    private var repository= ListaCategoriaRepository()
        fun listarCategoria():LiveData<List<Categoria>>{
            return repository.listarCategoria()
        }
}