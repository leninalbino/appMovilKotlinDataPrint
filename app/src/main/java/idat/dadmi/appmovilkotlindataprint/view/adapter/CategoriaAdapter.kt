package idat.dadmi.appmovilkotlindataprint.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import idat.dadmi.appmovilkotlindataprint.databinding.ItemCategoriasBinding
import idat.dadmi.appmovilkotlindataprint.retrofit.response.Categoria
import idat.dadmi.appmovilkotlindataprint.retrofit.response.ResponseProductos

class CategoriaAdapter(private val lscategoria:List<Categoria>)
                         :RecyclerView.Adapter<CategoriaAdapter.ViewHolder>(){
                   inner class ViewHolder(val binding:ItemCategoriasBinding):RecyclerView
    .ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemCategoriasBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(lscategoria[position]){
                binding.tvnombrecateria.text = nombreCate
            }
        }
    }

    override fun getItemCount(): Int {
        return lscategoria.size
    }
}