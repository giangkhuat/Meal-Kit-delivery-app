package com.ait.mealkitdeliveryapp.ui.dashboard

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
import com.ait.mealkitdeliveryapp.LogInActivity
import com.ait.mealkitdeliveryapp.MealDetailsActivity
import com.ait.mealkitdeliveryapp.adapter.orderAdapter
import com.ait.mealkitdeliveryapp.data.order
import com.ait.mealkitdeliveryapp.data.recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import com.ait.mealkitdeliveryapp.R
import com.google.firebase.auth.FirebaseUser


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    lateinit var userOrderAdapter: orderAdapter
    var items  = mutableListOf<recipe>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        checkUserLogIn()

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        userOrderAdapter = orderAdapter(activity, FirebaseAuth.getInstance().currentUser!!.uid)

        var linLayoutManager = LinearLayoutManager(activity)

        linLayoutManager.stackFromEnd = true

        view.recyclerOrder.layoutManager = linLayoutManager
        view.recyclerOrder.adapter = userOrderAdapter

        queryOrders()

    }

    fun checkUserLogIn() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            startActivity(Intent(activity, LogInActivity::class.java))
        }
    }

    fun queryOrders() {
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("Orders")

        var allOrderListener = query.addSnapshotListener(
            object: EventListener<QuerySnapshot> {
                override fun onEvent(querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {

                    if (e != null) {
                        Toast.makeText(activity, "listen error: ${e.message}", Toast.LENGTH_LONG).show()
                        return
                    }

                    for (dc in querySnapshot!!.getDocumentChanges()) {
                        when (dc.getType()) {
                            DocumentChange.Type.ADDED -> {
                                val newOrder = dc.document.toObject(order::class.java)
                                /* sb just added a post, make it visible to the adapter */
                                if (newOrder.uid == FirebaseAuth.getInstance().currentUser!!.uid) {
                                    userOrderAdapter.addOrder(newOrder)
                                }

                            }
                            DocumentChange.Type.MODIFIED -> {
                                Toast.makeText(activity, "update: ${dc.document.id}", Toast.LENGTH_LONG).show()
                            }
                            DocumentChange.Type.REMOVED -> {
                            //    postsAdapter.removePostByKey(dc.document.id)
                            }
                        }
                    }
                }
            })

    }



}