package com.dut.tpandroid2_1

import Cocktail
import ListeCocktails
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView

class ListeCocktailActivity : AppCompatActivity() {

    lateinit var listeCocktails : ListeCocktails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_cocktail)

        listeCocktails = intent.getParcelableExtra<ListeCocktails>("listeCocktails") as ListeCocktails

        var textViewTitre : TextView = findViewById(R.id.textViewTitre)
        textViewTitre.setText(listeCocktails.title)

        var listViewCocktails : ListView = findViewById<ListView>(R.id.listViewCocktails)
        val cocktailAdapter = CocktailAdapter(this, listeCocktails.cocktails as ArrayList<Cocktail>)

        listViewCocktails.adapter = cocktailAdapter
        cocktailAdapter?.notifyDataSetChanged()

    }
}