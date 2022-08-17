package idat.dadmi.appmovilkotlindataprint.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import idat.dadmi.appmovilkotlindataprint.db.DataPrintRoomDatabase
import idat.dadmi.appmovilkotlindataprint.db.entity.UsuarioEntity
import idat.dadmi.appmovilkotlindataprint.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application):AndroidViewModel(application) {

    private val repository : UsuarioRepository

    init {
        val usuarioDao = DataPrintRoomDatabase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(usuarioDao)
    }
    fun insertar (usuarioEntity: UsuarioEntity) = viewModelScope.launch (Dispatchers.IO){
        repository.insertar(usuarioEntity)
    }

    fun actualizar (usuarioEntity: UsuarioEntity)= viewModelScope.launch (Dispatchers.IO){
        repository.actualizar(usuarioEntity)
    }

    fun eliminartodo() = viewModelScope.launch(Dispatchers.IO){
        repository.eliminarTodo()
    }

    fun obtener():LiveData<UsuarioEntity>{
        return repository.obtener()
    }

}