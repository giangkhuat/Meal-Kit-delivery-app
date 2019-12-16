package com.ait.mealkitdeliveryapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ait.mealkitdeliveryapp.R
import kotlinx.android.synthetic.main.fragment_ingredients_tab.view.*

class IngredientsTab(var ingredients: String): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_ingredients_tab, container, false)
        root.tvIngredientsTab.setText(ingredients.replace("~!", System.lineSeparator()))
        return root

    }
}