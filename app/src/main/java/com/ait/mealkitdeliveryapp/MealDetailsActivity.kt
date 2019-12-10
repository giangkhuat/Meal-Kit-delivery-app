package com.ait.mealkitdeliveryapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ait.mealkitdeliveryapp.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_ingredients_tab.*
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.input.input
import kotlinx.android.synthetic.main.place_order_dialog.*


class MealDetailsActivity : AppCompatActivity() {

    companion object {
        var RECIPE = ""
        var INGREDIENTS = ""
        var DIRECTIONS = ""
        var NUTRITION = ""
        var PRICE = ""

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

        RECIPE = intent.getStringExtra("RecipeName")
        INGREDIENTS = intent.getStringExtra("Ingredients")
        DIRECTIONS = intent.getStringExtra("Directions")
        NUTRITION = intent.getStringExtra("Nutrition")
        PRICE = intent.getStringExtra("Price")


        fab.setOnClickListener { view ->
            showPlaceOrderDialog()
        }
    }

    private fun showPlaceOrderDialog(){
        val dialog: MaterialDialog = MaterialDialog(this).show {
            cornerRadius(16f)
            customView(R.layout.place_order_dialog)

            tvRecipeName.text = RECIPE
            tvCost.text = PRICE

            etItemQuantity.setOnKeyListener(View.OnKeyListener{ v, keyCode, event->
                if(event.action == KeyEvent.ACTION_UP){
                    //PerformCode
                    val cost =( PRICE.toFloat() * etItemQuantity.text.toString().toInt() ).toString()
                    tvCost.text = cost
                    return@OnKeyListener true
                }
                false
            })

            positiveButton(text = "Place Order")
            negativeButton(text = "Cancel")
        }
        dialog.show()
    }




}