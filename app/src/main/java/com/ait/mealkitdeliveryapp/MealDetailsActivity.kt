package com.ait.mealkitdeliveryapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ait.mealkitdeliveryapp.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_ingredients_tab.*

class MealDetailsActivity : AppCompatActivity() {

    companion object {
        var INGREDIENTS = ""
        var DIRECTIONS = ""
        var NUTRITION = ""

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        val intent = getIntent()//intent.getStringExtra("Ingredients")

        INGREDIENTS = intent.getStringExtra("Ingredients")
        DIRECTIONS = intent.getStringExtra("Directions")
        NUTRITION = intent.getStringExtra("Nutrition")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


}