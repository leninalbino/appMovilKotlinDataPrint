package idat.dadmi.appmovilkotlindataprint.view.adapter

import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito

interface OnclickCarritoItem {
    fun OnDeleteItem(id:Long)
    fun OnUpdateAmount(cantidad:Int, id:Long)
}