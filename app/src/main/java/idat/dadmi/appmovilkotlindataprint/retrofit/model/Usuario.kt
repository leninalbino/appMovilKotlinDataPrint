package idat.dadmi.appmovilkotlindataprint.retrofit.model

import android.os.Parcelable
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Role
import kotlinx.parcelize.Parcelize

@Parcelize
class Usuario(
    val idusuarios: Long,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val telefono: String,
    val direcc: String,
    val correo: String,
):Parcelable