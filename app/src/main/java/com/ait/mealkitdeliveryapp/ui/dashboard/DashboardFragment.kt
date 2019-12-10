package com.ait.mealkitdeliveryapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ait.mealkitdeliveryapp.R
import com.ait.mealkitdeliveryapp.adapter.orderAdapter
import com.ait.mealkitdeliveryapp.adapter.recipeAdapter
import com.ait.mealkitdeliveryapp.data.recipe
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

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
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        userOrderAdapter = orderAdapter(this.context)

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

    private fun queryOrders() {

    }



}