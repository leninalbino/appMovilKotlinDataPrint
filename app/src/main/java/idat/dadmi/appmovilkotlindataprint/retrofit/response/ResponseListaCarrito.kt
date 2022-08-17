package idat.dadmi.appmovilkotlindataprint.retrofit.response

data class ResponseListaCarrito(
    val idCarrito: Long,
    val cantidad: Long,
    val caracteristica: Caracteristica1,
    val usuario: Usuario,
)

data class Caracteristica1(
    val idCaracteristica: Long,
    val descriCaract: String,
    val cantidCaract: Long,
    val precioCaract: Double,
    val producto: Producto,
)

data class Producto(
    val idProductoPro: Long,
    val imageProp: String,
    val nombrePro: String,
    val estadoPro: Boolean,
    //val categoria: Categoria1,
    //val caracteristicas: List<Caracteristica2>,
)

data class Categoria1(
    val idcategorias: Long,
    val nombreCate: String,
)

data class Caracteristica2(
    val idCaracteristica: Long,
    val descriCaract: String,
    val cantidCaract: Long,
    val precioCaract: Double,
)

data class Usuario(
    val idusuarios: Long,
    val nombre: Any?,
    val apellido: Any?,
    val dni: Any?,
    val telefono: Any?,
    val direcc: Any?,
    val correo: String,
    val clave: String,
    val fecrea: Any?,
    val tokenVerificacion: Any?,
    val enable: Boolean,
    val roles: List<Role>,
)

data class Role(
    val idrol: Long,
    val rol: String,
)

