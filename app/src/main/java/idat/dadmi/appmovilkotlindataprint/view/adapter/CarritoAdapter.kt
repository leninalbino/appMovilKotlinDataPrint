package idat.dadmi.appmovilkotlindataprint.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import idat.dadmi.appmovilkotlindataprint.databinding.ListItemCarritoBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseListaCarrito
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje

class CarritoAdapter (private val clickCarritoItem:OnclickCarritoItem,
                      private var lstcarritoitem:List<ResponseListaCarrito>): RecyclerView
                                                    .Adapter<CarritoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemCarritoBinding):
                                                    RecyclerView.ViewHolder(binding.root)

    private lateinit var _binding:ListItemCarritoBinding
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemCarritoBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var subtotal :Double = 0.0
        var idCarrito:Long=0
        var mCantidad:Int=0
        with(holder){
            with(lstcarritoitem[position]){
                binding.tvnomproduct.text = caracteristica.producto.nombrePro
                binding.tvcaravterisitca.text = caracteristica.descriCaract
                binding.tvpreci.text = caracteristica.precioCaract.toString()
                mCantidad=cantidad.toInt()
                binding.etcanti.setText(cantidad.toString())
                subtotal =cantidad * caracteristica.precioCaract
                val roun = String.format("%.2f",subtotal)
                binding.tvsubtotal.text = roun
                Glide.with(holder.itemView.context)
                    .load(caracteristica.producto.imageProp)
                    .into(binding.ivproducto)

                idCarrito = lstcarritoitem[position].idCarrito
            }
            holder.binding.btnactualizaritem.setOnClickListener{
                var okCantidad=true

                if(holder.binding.etcanti.toString().trim().isEmpty()){
                    holder.binding.etcanti.isFocusableInTouchMode=true
                    holder.binding.etcanti.requestFocus()
                    okCantidad= false
                }
                if(okCantidad){
                    clickCarritoItem.OnUpdateAmount(mCantidad,idCarrito)

                }else{
                    AppMensaje.enviarMensaje(binding.root,
                        "Cantidad ingresar es invalido", TipoMensaje.ERROR)
                }
            }

            holder.binding.btneliminar.setOnClickListener{
                clickCarritoItem.OnDeleteItem(idCarrito)
            }
        }



    }

    private fun obtenerMensaje(responseAgregarCarrito: ResponseAgregarCarrito) {
        AppMensaje.enviarMensaje(binding.root,
           ""+responseAgregarCarrito.mensaje, TipoMensaje.EXITO)
    }

    override fun getItemCount(): Int {
        return lstcarritoitem.size
    }


}