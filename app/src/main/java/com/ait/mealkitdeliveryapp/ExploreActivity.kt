package com.ait.mealkitdeliveryapp

import android.os.Bundle
import android.widget.Toast
import com.google.api.Distribution


import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ait.mealkitdeliveryapp.adapter.recipeAdapter
import com.ait.mealkitdeliveryapp.data.recipe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.EventListener
import kotlinx.android.synthetic.main.activity_explore.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class ExploreActivity : AppCompatActivity() {

    lateinit var rAdapter: recipeAdapter
    var items  = mutableListOf<recipe>()

    // var items = AppDatabase.getInstance(this@ScrollingActivity).ItemDao().getALlItems()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        setSupportActionBar(toolbar)

        rAdapter = recipeAdapter(this)
        var linLayoutManager = LinearLayoutManager(this)
        linLayoutManager.stackFromEnd = true

        recyclerRecipes.layoutManager = linLayoutManager
        recyclerRecipes.adapter = rAdapter
        queryPosts()
        //uploadRecipe()

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


    private fun queryPosts() {
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("RecipeBook")

        var allPostsListener = query.addSnapshotListener(
            object: EventListener<QuerySnapshot> {
                override fun onEvent(querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {

//                    if (e != null) {
//                        Toast.makeText(this@ForumActivity, "listen error: ${e.message}", Toast.LENGTH_LONG).show()
//                        return
//                    }

                    for (dc in querySnapshot!!.getDocumentChanges()) {
                        when (dc.getType()) {
                            DocumentChange.Type.ADDED -> {
                                val recipe = dc.document.toObject(recipe::class.java)
//                                recipeAdapter.addRecipe(recipe, dc.document.id)
                                rAdapter.addRecipe(recipe, dc.document.id)
                            }
                            DocumentChange.Type.MODIFIED -> {
                                Toast.makeText(this@ExploreActivity, "update: ${dc.document.id}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            })

    }
}
