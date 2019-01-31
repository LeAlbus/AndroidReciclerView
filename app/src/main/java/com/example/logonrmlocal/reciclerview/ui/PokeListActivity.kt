package com.example.logonrmlocal.reciclerview.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.logonrmlocal.reciclerview.R
import com.example.logonrmlocal.reciclerview.api.getPokemonAPI
import com.example.logonrmlocal.reciclerview.model.ListResponse
import com.example.logonrmlocal.reciclerview.model.Pokemon
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_poke_list.*

class PokeListActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_list)

        this.loadData()
    }

    private fun showList(pokemons: List<Pokemon>){
        rvPokedex.adapter = PokeListAdapter(this, pokemons)
        rvPokedex.layoutManager = LinearLayoutManager(this)
    }

    private fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun loadData(){
        getPokemonAPI()
                .search(151)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<ListResponse> {
                    override fun onComplete() {
                        Log.i("ListActivity", "COMPLETE")
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: ListResponse) {
                        showList(t.pokeList)
                    }

                    override fun onError(e: Throwable) {
                        showError(e.message!!)
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
