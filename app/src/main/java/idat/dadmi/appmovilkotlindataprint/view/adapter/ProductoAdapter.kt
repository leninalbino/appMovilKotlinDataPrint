package idat.dadmi.appmovilkotlindataprint.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ItemProductosBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.model.Caracteristica
import idat.dadmi.appmovilkotlindataprint.retrofit.model.Categoria
import idat.dadmi.appmovilkotlindataprint.retrofit.model.Producto
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseProductos
import kotlinx.coroutines.flow.callbackFlow
import java.util.*
import kotlin.collections.ArrayList


class ProductoAdapter(private var lstproducto: List<ResponseProductos>)
                        :RecyclerView.Adapter<ProductoAdapter.ViewHolder>(){

    private var nombreCate :Categoria? = null
    private var descriCaract : MutableList<Caracteristica> = ArrayList()

    inner class ViewHolder(val binding: ItemProductosBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductosBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       with(holder){
            with(lstproducto[position]){

                binding.tvnomproducto.text = nombrePro
                binding.tvcategoria.text = categoria.nombreCate
                Glide.with(holder.itemView.context)
                   .load(imageProp)
                    .into(binding.ivproducto)
            }
        }

        holder.binding.layoutitem.setOnClickListener{view: View ->

            val idProductoPro = lstproducto[position].idProductoPro
            val imageProp  = lstproducto[position].imageProp
            nombreCate = Categoria(lstproducto[position].categoria.nombreCate)
            val nombrePro = holder.binding.tvnomproducto.text.toString()

            //Agregar las cararacteristicas a la variable descriCaract
            lstproducto[position].caracteristicas.forEach{
                it -> descriCaract.add(
                    Caracteristica(it.idCaracteristica,
                    it.descriCaract,it.cantidCaract, it.precioCaract)
                )
            }
            // creamos un nuevo objeto para enviar al fragmento detalle
            val producto = Producto (
                idProductoPro,
                imageProp,
                nombrePro,
                nombreCate!!,
                descriCaract,

            )

            val bundle = bundleOf("producto" to producto)
            view.findNavController().navigate(R.id.detalleProductoFragment,bundle)
        }
    }


    override fun getItemCount()= lstproducto.size




}