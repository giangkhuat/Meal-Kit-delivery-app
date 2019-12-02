package com.ait.mealkitdeliveryapp

import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ait.mealkitdeliveryapp.adapter.recipeAdapter
import com.ait.mealkitdeliveryapp.data.recipe
import com.google.firebase.firestore.EventListener
import kotlinx.android.synthetic.main.activity_explore.*

class ExploreActivity : AppCompatActivity() {

    lateinit var rAdapter: recipeAdapter
    var items  = mutableListOf<recipe>()

    // var items = AppDatabase.getInstance(this@ScrollingActivity).ItemDao().getALlItems()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        setSupportActionBar(toolbar)


        rAdapter = recipeAdapter(this)

        var linLayoutManger = LinearLayoutManager(this)
        linLayoutManger.stackFromEnd = true

        recyclerRecipes.layoutManager = linLayoutManger

        recyclerRecipes.adapter = rAdapter

        queryPosts()
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
