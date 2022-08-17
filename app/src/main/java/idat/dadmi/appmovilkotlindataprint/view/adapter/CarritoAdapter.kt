package idat.dadmi.appmovilkotlindataprint.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityCarritoBinding
import idat.dadmi.appmovilkotlindataprint.databinding.ListItemCarritoBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseAgregarCarrito
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseListaCarrito
import idat.dadmi.appmovilkotlindataprint.utilitarios.AppMensaje
import idat.dadmi.appmovilkotlindataprint.utilitarios.TipoMensaje
import idat.dadmi.appmovilkotlindataprint.viewmodel.CarritoViewModel
import kotlin.math.roundToLong

class CarritoAdapter (private var lstcarritoitem:List<ResponseListaCarrito>): RecyclerView
                                                    .Adapter<CarritoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemCarritoBinding):
                                                    RecyclerView.ViewHolder(binding.root)

    // var carritoViewModel: CarritoViewModel
    //init  {
     //    carritoViewModel = ViewModelProvider(this).get(CarritoViewModel::class.java)
    //    carritoViewModel.responseAgregarCarrito.observe(this, Observer {
     //       obtenerMensaje(it!!)
            //Toast.makeText(context, "Mensaje: $it", Toast.LENGTH_LONG).show()
     //   })
    //}

    private lateinit var _binding:ListItemCarritoBinding
    private val binding get() = _binding!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemCarritoBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)
       // binding.btnactualizaritem.setOnClickListener(this)

        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var subtotal :Double = 0.0
        var idCarrito:Long=0
        with(holder){
            with(lstcarritoitem[position]){
                binding.tvnomproduct.text = caracteristica.producto.nombrePro
                binding.tvcaravterisitca.text = caracteristica.descriCaract
                binding.tvpreci.text = caracteristica.precioCaract.toString()
                binding.etcanti.setText(cantidad.toString())
                subtotal =cantidad * caracteristica.precioCaract
                binding.tvsubtotal.text = subtotal.toString()
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
                    //carritoViewModel.updateAmountItem(holder.binding.etcanti.toString().toInt(),idCarrito)
                }else{
                    AppMensaje.enviarMensaje(binding.root,
                        "Cantidad ingresar es invalido", TipoMensaje.ERROR)
                }
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