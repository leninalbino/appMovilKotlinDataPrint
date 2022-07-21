package idat.dadmi.appmovilkotlindataprint.retrofit.response

data class ResponseProductos(
    val idProductoPro: Long,
    val imageProp: String,
    val nombrePro: String,
    val estadoPro: Boolean,
    val categoria: Categoria,
    val caracteristicas: List<Caracteristica>,
)

data class Categoria(
    val idcategorias: Long,
    val nombreCate: String,
)

data class Caracteristica(
    val idCaracteristica: Long,
    val descriCaract: String,
    val cantidCaract: Long,
    val precioCaract: Double,
)
