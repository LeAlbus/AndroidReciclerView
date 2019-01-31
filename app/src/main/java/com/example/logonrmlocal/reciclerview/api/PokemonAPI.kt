package com.example.logonrmlocal.reciclerview.api

import com.example.logonrmlocal.reciclerview.model.ListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI{

    @GET("/api/pokemon")

    fun search(@Query("size") size: Int): Observable<ListResponse>
}