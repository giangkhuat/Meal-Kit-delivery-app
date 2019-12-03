package com.ait.mealkitdeliveryapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ait.mealkitdeliveryapp.R
import kotlinx.android.synthetic.main.fragment_directions_tab.view.*

class DirectionsTab(var directions: String): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        tvIngredientsTab.text = ingredients
        val root = inflater!!.inflate(R.layout.fragment_directions_tab, container, false)
        root.tvDirectionsTab.setText(directions)
        return root

    }
}