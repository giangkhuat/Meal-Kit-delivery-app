package com.ait.mealkitdeliveryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ait.mealkitdeliveryapp.R
import com.ait.mealkitdeliveryapp.data.recipe
import kotlinx.android.synthetic.main.recipe_card.view.*
import java.util.*

class recipeAdapter : RecyclerView.Adapter<recipeAdapter.ViewHolder> {

    var recipelist  = mutableListOf<recipe>()
    var context: Context

    constructor(context: Context, items: List<recipe>) {
        this.context = context
        recipelist.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /* define layout of one ShopItem  outside actiity */
        var item = LayoutInflater.from(context).inflate(
            R.layout.recipe_card, parent, false
        )
        return ViewHolder(item)
    }
    override fun getItemCount(): Int {
        return recipelist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = recipelist.get(holder.adapterPosition)

        holder.recipeCard.setOnClickListener{
            //intent
        }
    }

    /*
    fun deleteItem(index: Int) {
        Thread {
            AppDatabase.getInstance(context).ItemDao().deleteItem(shoplist[index])
            (context as ScrollingActivity).runOnUiThread()  {
                shoplist.removeAt(index)
                notifyItemRemoved(index)
            }
        }.start()
    }

    */
    fun addItem(recipe : recipe) {
        recipelist.add(recipe)
        notifyItemInserted(recipelist.lastIndex)
    }

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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProduct_name = itemView.tvProduct_name
        var tvDescription = itemView.tvDescription
        var tvPrice = itemView.tvPrice
        var ivImage = itemView.ivImage
        var recipeCard = itemView.recipeCard
    }

}