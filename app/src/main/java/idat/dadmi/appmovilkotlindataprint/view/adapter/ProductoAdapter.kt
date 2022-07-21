package idat.dadmi.appmovilkotlindataprint.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import idat.dadmi.appmovilkotlindataprint.databinding.ItemProductosBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Caracteristica
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseProductos


class ProductoAdapter(private var lstproducto: List<ResponseProductos>)
                        :RecyclerView.Adapter<ProductoAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemProductosBinding):
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductosBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       with(holder){
            with(lstproducto[position]){

                binding.tvnomproducto.text = nombrePro
                binding.tvstock.text  =caracteristicas.forEach { car-> car.cantidCaract }.toString()
                //binding.tvcantidad.text  = cantidCaract
                binding.tvprecio.text  = caracteristicas.forEach { car-> car.precioCaract }.toString()
                Glide.with(holder.itemView.context)
                   .load("")
                    .into(binding.ivproducto)
            }
        }


    }

    override fun getItemCount()= lstproducto.size

}