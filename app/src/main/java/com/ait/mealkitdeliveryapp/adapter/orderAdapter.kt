package com.ait.mealkitdeliveryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ait.mealkitdeliveryapp.ExploreActivity
import com.ait.mealkitdeliveryapp.R
import com.ait.mealkitdeliveryapp.data.order
import com.ait.mealkitdeliveryapp.data.recipe
import kotlinx.android.synthetic.main.order_card.view.*
import kotlinx.android.synthetic.main.recipe_card.view.*


class orderAdapter(private val context: Context?, private val uid: String) : RecyclerView.Adapter<orderAdapter.ViewHolder>() {

   private var orderList = mutableListOf<order>()
  //  private var recipeKeys = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = LayoutInflater.from(context).inflate(
            R.layout.order_card, parent, false
        )
        return ViewHolder(item)
    }
    override fun getItemCount(): Int {
        if (orderList == null) {
            return 0;
        }
        return orderList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = orderList.get(holder.adapterPosition)
        holder.orderName.text = item.recipeName.toString()
        holder.price.text = "Cost: " + item.cost.toString()
        holder.orderAmount.text = "Amt: " + item.quantity.toString()
        holder.address.text = item.address.toString()
        holder.orderDate.text = "Order Date: " + item.dateOrder
        holder.btnDel.setOnClickListener() {
            // deleteItem(holder.adapterPosition)
        }
    }

    // need to add to database as well

    fun addOrder(UserOrder : order) {
        orderList.add(0, UserOrder)
        //recipeKeys.add(key)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var orderName = itemView.tvOrderName
        var price = itemView.tvOrderPrice
        var btnDel = itemView.btnDelete
        var orderAmount = itemView.tvAmount
        var address = itemView.tvAddress
        var orderDate = itemView.tvOrderDate
    }

    /*
    fun deleteItem(index: Int) {
        Thread {
            (context as ExploreActivity).runOnUiThread()  {
                orderList.removeAt(index)
                notifyItemRemoved(index)
            }
        }.start()
    }

    fun updateItemOnPosition(item: recipe, index: Int) {
        shoplist.set(index, item)
        notifyItemChanged(index)
    }
*/

    /*
    fun deleteAllItems() {
        Thread {
            AppDatabase.getInstance(context).ItemDao().deleteAll()
            (context as ScrollingActivity).runOnUiThread {
                shoplist.clear()
                notifyDataSetChanged()
            }
        }.start()
    }
*/

    /* Update recyclerview
    fun updateItemOnPosition(item: ShopItem, index: Int) {
        shoplist.set(index, item)
        notifyItemChanged(index)
    }

    /* Update database */
    fun updateItem(item: ShopItem) {
        Thread {
            AppDatabase.getInstance(context).ItemDao().updateItem(item)
        }.start()

    }


     */




}