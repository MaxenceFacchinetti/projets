package com.dut.tpandroid2_1

import ListeCocktails
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ChargementActivity : AppCompatActivity(), retrofit2.Callback<ListeCocktails> {

    val urlListeCocktails = "https://perso.univ-lyon1.fr/lionel.buathier/cocktails/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chargement)

        val imageViewCocktail: ImageView = findViewById(R.id.imageViewCocktail)
        Picasso.get().load("https://static1.villaschweppes.com/articles//6/51/46/6/@/364165-la-recette-du-cocktail-madeleine-600x600-2.jpeg").into(imageViewCocktail)

        val gson: Gson = GsonBuilder().create()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(urlListeCocktails)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val service = retrofit.create(CocktailService::class.java)
        val call = service.getCocktails()

        call.enqueue(this);

    }

    override fun onFailure(call: Call<ListeCocktails>, t: Throwable) {
        Log.d("maxe","GRONNI BAR " + t.message);
    }

    override fun onResponse(call: Call<ListeCocktails>, response: Response<ListeCocktails>) {
        val listeCocktails = response.body();
        val intentListeCocktails = Intent(this,ListeCocktailActivity::class.java)
        intentListeCocktails.putExtra("listeCocktails",listeCocktails)
        startActivity(intentListeCocktails)
}
}
