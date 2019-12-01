package com.ait.mealkitdeliveryapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ait.mealkitdeliveryapp.adapter.recipeAdapter
import com.ait.mealkitdeliveryapp.data.recipe
import kotlinx.android.synthetic.main.activity_explore.*

class ExploreActivity : AppCompatActivity() {

    lateinit var adapter: recipeAdapter
    var items  = mutableListOf<recipe>()

    // var items = AppDatabase.getInstance(this@ScrollingActivity).ItemDao().getALlItems()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        setSupportActionBar(toolbar)
        adapter = recipeAdapter(this, items)
        recyclerItem.adapter = adapter

    }
}
