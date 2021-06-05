package com.manektech.ui.restaurantList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manektech.data.entities.RestaurantItem
import com.manektech.databinding.RestaurantItemBinding

class RestaurantListAdapter(private val listener: RestaurantItemListener) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    interface RestaurantItemListener {
        fun onClickedRestaurant(characterId: RestaurantItem, isMapclick: Boolean)
    }

    private val items = ArrayList<RestaurantItem>()

    fun setItems(items: ArrayList<RestaurantItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding: RestaurantItemBinding =
            RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
        holder.bind(items[position])
}

class RestaurantViewHolder(
    private val itemBinding: RestaurantItemBinding,
    private val listener: RestaurantListAdapter.RestaurantItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var restaurantItem: RestaurantItem

    init {
        var isMapclick: Boolean
        itemBinding.root.setOnClickListener {
            isMapclick = false
            listener.onClickedRestaurant(restaurantItem,isMapclick)
        }
        itemBinding.imagemap.setOnClickListener {
            isMapclick = true
            listener.onClickedRestaurant(restaurantItem,isMapclick)

        }
    }

    fun bind(item: RestaurantItem) {
        this.restaurantItem = item
        itemBinding.title.text = item.title
        restaurantItem.rating?.let {
            itemBinding.ratingBar.rating= it.toFloat()
        }?:kotlin.run {
            itemBinding.ratingBar.rating= 0.0f
        }
    }

    override fun onClick(v: View?) {
      //  listener.onClickedRestaurant(restaurantItem)
    }
}

