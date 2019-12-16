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
import android.widget.Toast
import com.ait.mealkitdeliveryapp.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_ingredients_tab.*
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.input.input
import com.ait.mealkitdeliveryapp.adapter.orderAdapter
import com.ait.mealkitdeliveryapp.data.order
import com.ait.mealkitdeliveryapp.ui.dashboard.DashboardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.place_order_dialog.*
import java.text.SimpleDateFormat
import java.util.*


class MealDetailsActivity : AppCompatActivity() {

    companion object {
        var RECIPE = ""
        var INGREDIENTS = ""
        var DIRECTIONS = ""
        var NUTRITION = ""
        var PRICE = ""

    }

    lateinit var orderAdapter: orderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val btnOrder: FloatingActionButton = findViewById(R.id.fab)


        val intent = getIntent()

        RECIPE = intent.getStringExtra("RecipeName")
        INGREDIENTS = intent.getStringExtra("Ingredients")
        DIRECTIONS = intent.getStringExtra("Directions")
        NUTRITION = intent.getStringExtra("Nutrition")
        PRICE = intent.getStringExtra("Price")


        btnOrder.setOnClickListener { view ->
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                startActivity(Intent(this@MealDetailsActivity, LogInActivity::class.java))
            } else {
                showPlaceOrderDialog()
            }
        }
    }

    private fun showPlaceOrderDialog(){
        val dialog: MaterialDialog = MaterialDialog(this).show {
            cornerRadius(16f)
            customView(R.layout.place_order_dialog)

            tvRecipeName.text = RECIPE
            tvCost.text = PRICE

            val orderDate = Date(System.currentTimeMillis())
            val sdf = SimpleDateFormat("MMM d, yyyy")

            etItemQuantity.setOnKeyListener(View.OnKeyListener{ v, keyCode, event->
                if(event.action == KeyEvent.ACTION_UP){
                    //PerformCode
                    val cost =( PRICE.toFloat() * etItemQuantity.text.toString().toInt() ).toString()
                    tvCost.text = cost
                    return@OnKeyListener true
                }
                false
            })


            positiveButton(text = getString(R.string.place_order_btn)) { dialog ->

                var order = order(
                    FirebaseAuth.getInstance().currentUser!!.uid,
                    tvRecipeName.text.toString(),
                    etItemQuantity.text.toString().toInt(),
                    tvCost.text.toString().toFloat(),
                    etDeliveryAddress.text.toString(),
                    sdf.format(orderDate)
                )

                var ordersCollection =
                    FirebaseFirestore.getInstance().collection("Orders")

                ordersCollection.add(order).addOnSuccessListener {
                    finish()
                }.addOnFailureListener {
                }

            }
            negativeButton(text = getString(R.string.cancel_order_btn))
        }
        dialog.show()
    }




}