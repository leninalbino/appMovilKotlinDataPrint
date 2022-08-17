package idat.dadmi.appmovilkotlindataprint.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(
    @PrimaryKey
    val id:Int,
    val token:String,
    val user:String,
    val password:String,
    val correo:String
)
