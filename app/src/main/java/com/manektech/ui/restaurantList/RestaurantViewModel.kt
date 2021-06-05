package com.manektech.ui.restaurantList

import androidx.lifecycle.ViewModel
import com.manektech.data.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {
    val restaurant = repository.getRestaurant()
}
