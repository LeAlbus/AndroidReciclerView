package com.example.logonrmlocal.reciclerview.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logonrmlocal.reciclerview.R
import com.example.logonrmlocal.reciclerview.api.getPicassoAuth
import com.example.logonrmlocal.reciclerview.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_row.view.*

class PokeListAdapter(
        private val context: Context,
        private val pokemons: List<Pokemon>,
        private val listener: (Pokemon) -> Unit

): RecyclerView.Adapter<PokeListAdapter.PokemonViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.pokemon_row, parent, false)

        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.count()
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bindView(pokemon, listener)
    }

    class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(pokemon: Pokemon,
                     listener: (Pokemon) -> Unit) = with(itemView){

            PokeName.text = pokemon.pokeName

            getPicassoAuth(itemView.context)
                    .load("https://pokedexdx.herokuapp.com${pokemon.pokeImage}")
                    .into(PokePic)

            setOnClickListener {listener(pokemon)}

        }
    }


}
