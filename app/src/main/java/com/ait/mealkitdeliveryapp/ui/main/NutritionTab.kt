package com.ait.mealkitdeliveryapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ait.mealkitdeliveryapp.R
import kotlinx.android.synthetic.main.fragment_nutrition_tab.view.*

class NutritionTab(var nutrition: String): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_nutrition_tab, container, false)
        root.tvNutritionTab.setText(nutrition.replace("~!", System.lineSeparator()))
        return root

    }
}