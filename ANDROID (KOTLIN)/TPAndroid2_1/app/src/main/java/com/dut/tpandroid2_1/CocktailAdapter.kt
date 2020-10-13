package com.dut.tpandroid2_1

import Cocktail
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.w3c.dom.Text

class CocktailAdapter(private var activity: Activity, private var cocktails: ArrayList<Cocktail>) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var textViewNomCocktail = convertView?.findViewById<TextView>(R.id.textViewNomCocktail)
        var textViewAlcool  = convertView?.findViewById<TextView>(R.id.textViewAlcool)

        val view: View?
        if(convertView == null){
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.adapter_cocktail, null)
        }
        else{
            view = convertView
        }

        view?.findViewById<TextView>(R.id.textViewNomCocktail)?.setText(cocktails.get(position).name)
        val alcoolise : String
        if(cocktails.get(position).alcool){
            alcoolise = "🟢"
        }
        else{
            alcoolise = "🔴"
        }

        view?.findViewById<TextView>(R.id.textViewAlcool)?.setText(alcoolise)

        return view

    }

    override fun getItem(position: Int): Any {
        return cocktails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return cocktails.size
    }
}