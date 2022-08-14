package idat.dadmi.appmovilkotlindataprint.retrofit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Caracteristica (
    val idCaracteristica: Long,
    val descriCaract: String,
    val cantidCaract: Long,
    val precioCaract: Double
): Parcelable