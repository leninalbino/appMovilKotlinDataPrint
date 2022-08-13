package idat.dadmi.appmovilkotlindataprint.repository

import androidx.lifecycle.LiveData
import idat.dadmi.appmovilkotlindataprint.db.dao.UsuarioDao
import idat.dadmi.appmovilkotlindataprint.db.entity.UsuarioEntity

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun insertar(usuarioEntity: UsuarioEntity){
        usuarioDao.insertar(usuarioEntity)
    }

    suspend fun actualizar(usuarioEntity: UsuarioEntity){
        usuarioDao.actualizar(usuarioEntity)
    }

    suspend fun eliminarTodo(){
        usuarioDao.eliminarTodo()
    }

    fun obtener():LiveData<UsuarioEntity>{
        return usuarioDao.obtener()
    }
}