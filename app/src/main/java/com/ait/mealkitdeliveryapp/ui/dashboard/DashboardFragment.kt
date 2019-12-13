package com.ait.mealkitdeliveryapp.ui.dashboard

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
import com.ait.mealkitdeliveryapp.MealDetailsActivity
import com.ait.mealkitdeliveryapp.adapter.orderAdapter
import com.ait.mealkitdeliveryapp.data.order
import com.ait.mealkitdeliveryapp.data.recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import com.ait.mealkitdeliveryapp.R


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


        return root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        userOrderAdapter = orderAdapter(activity, FirebaseAuth.getInstance().currentUser!!.uid)

        var linLayoutManager = LinearLayoutManager(activity)

        linLayoutManager.stackFromEnd = true

        view.recyclerOrder.layoutManager = linLayoutManager
        // Set adapter
        view.recyclerOrder.adapter = userOrderAdapter

        /*
        Authenticate user
        Only user can see their own orders
         */
        queryOrders()

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
                                userOrderAdapter.addOrder(newOrder)
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