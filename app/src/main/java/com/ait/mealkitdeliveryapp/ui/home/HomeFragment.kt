package com.ait.mealkitdeliveryapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.ait.mealkitdeliveryapp.LogInActivity
import com.ait.mealkitdeliveryapp.R
import com.ait.mealkitdeliveryapp.adapter.recipeAdapter
import com.ait.mealkitdeliveryapp.data.recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    lateinit var rAdapter: recipeAdapter
    var items  = mutableListOf<recipe>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
       // val textView: TextView = root.findViewById(R.id.text_home)
       // homeViewModel.text.observe(this, Observer {
        //    textView.text = it
        //})
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        rAdapter = recipeAdapter(this.context!!)

        var linLayoutManager = LinearLayoutManager(activity)

        linLayoutManager.stackFromEnd = true

        view.recyclerRecipes.layoutManager = linLayoutManager
        // Set adapter
        view.recyclerRecipes.adapter = rAdapter
        view.logInSign.setOnClickListener {
            try {
                FirebaseAuth.getInstance().currentUser!!.uid
                showLogoutDialog(requireContext())
                Toast.makeText(this.context, "Log out", Toast.LENGTH_LONG).show()
            } catch (ex:Exception) {
                  startActivity(Intent(activity, LogInActivity::class.java))
                Toast.makeText(this.context, "Login", Toast.LENGTH_LONG).show()
            }
        }
        queryPosts()
    }
/*

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
            Toast.makeText(this@Explore, "Success creating post", Toast.LENGTH_LONG).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@Explore, "Error : ${it.message}", Toast.LENGTH_LONG).show()
        }
    }
 */
    fun showLogoutDialog(context: Context){
        val dialog: MaterialDialog = MaterialDialog(context).show {
            cornerRadius(16f)
            title(text = "Logging Out")
            message(text = "Are you sure?")

            positiveButton(text = "Ok") { dialog ->
                FirebaseAuth.getInstance().signOut()
            }

            negativeButton(text = "Cancel")
        }
    }

    fun queryPosts() {
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("RecipeBook")

        var allPostsListener = query.addSnapshotListener(
            object: EventListener<QuerySnapshot> {
                override fun onEvent(querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    for (dc in querySnapshot!!.getDocumentChanges()) {
                        when (dc.getType()) {
                            DocumentChange.Type.ADDED -> {
                                val recipe = dc.document.toObject(recipe::class.java)
//                                recipeAdapter.addRecipe(recipe, dc.document.id)
                                rAdapter.addRecipe(recipe, dc.document.id)
                            }
                            DocumentChange.Type.MODIFIED -> {
                                Toast.makeText(activity, "update: ${dc.document.id}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            })

    }

}