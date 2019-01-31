package com.example.logonrmlocal.reciclerview.model

import com.google.gson.annotations.SerializedName

data class ListResponse(@SerializedName("content") val pokeList: List<Pokemon>)

data class Pokemon(
        @SerializedName("number") val pokedexNum: String,
        @SerializedName("name") val pokeName: String,
        @SerializedName("imageURL") val pokeImage: String)