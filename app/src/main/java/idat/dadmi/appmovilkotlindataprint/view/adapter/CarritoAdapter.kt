package idat.dadmi.appmovilkotlindataprint.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
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

                    mCantidad = binding.etcanti.text.toString().toInt()
                    println(mCantidad)
                    clickCarritoItem.OnUpdateAmount(mCantidad,idCarrito)

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