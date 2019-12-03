package com.ait.mealkitdeliveryapp

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ait.mealkitdeliveryapp.adapter.recipeAdapter
import com.ait.mealkitdeliveryapp.data.recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_explore.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



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
        uploadRecipe()

    }

    /*
    var name : String = "",
    var ingredient : String = "",
    var description: String = "",
    var instruction: String = "",
    var nutrition : String = "",
    var category : Int = -1,
    var price : Double = 0.0
     */

    private fun uploadRecipe() {
        var recipeObj = recipe(
            /* user id */
            //FirebaseAuth.getInstance().currentUser!!.uid,
            //FirebaseAuth.getInstance().currentUser!!.displayName!!,
            "Nama Chocolate",
            "Milk, Sugar",
            "",
            "Carb a lot",
            "Dairy",
            0, 9.0
        )

        val db = FirebaseFirestore.getInstance()
        /* collection is the folder in database */
        var recipeCollection = db.collection("recipes")
        recipeCollection.add(recipeObj).addOnSuccessListener {
            Toast.makeText(this@ExploreActivity, "Success creating post", Toast.LENGTH_LONG).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@ExploreActivity, "Error : ${it.message}", Toast.LENGTH_LONG).show()
        }
    }

}
