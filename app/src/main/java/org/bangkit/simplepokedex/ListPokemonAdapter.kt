package org.bangkit.simplepokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.bangkit.simplepokedex.databinding.ItemRowPokemonBinding

class ListPokemonAdapter(
    private val listPokemon: ArrayList<Pokemon>,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<ListPokemonAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowPokemonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() = listPokemon.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pokemon = listPokemon[position]
        holder.binding.tvItemName.text = pokemon.name
        holder.binding.tvItemDescription.text = pokemon.description
        Glide.with(holder.itemView.context)
            .load(pokemon.photo)
            .into(holder.binding.imgItemPhoto)
        holder.itemView.setOnClickListener {
            onClick()
        }
    }
}