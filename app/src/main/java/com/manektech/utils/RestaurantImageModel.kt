package com.manektech.utils

import com.manektech.data.entities.Image
import com.manektech.data.entities.RestaurantItem

data class RestaurantImageModel(
    var restaurantItemList : ArrayList<RestaurantItem>,
    var imageList: ArrayList<Image>
)