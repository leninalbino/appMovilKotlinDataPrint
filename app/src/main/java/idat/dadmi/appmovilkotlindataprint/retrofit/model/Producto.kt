package idat.dadmi.appmovilkotlindataprint.retrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Producto(
    val idProductoPro: Long,
    val imageProp: String,
    val nombrePro: String,
    val categoria: Categoria,
    val caracteristicas: List<Caracteristica>,
    ):Parcelable {
}
