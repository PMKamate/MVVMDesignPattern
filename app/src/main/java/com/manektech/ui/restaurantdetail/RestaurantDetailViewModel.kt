package com.manektech.ui.restaurantdetail

import androidx.lifecycle.*
import com.manektech.data.entities.Image
import com.manektech.data.entities.RestaurantItem
import com.manektech.data.repository.RestaurantRepository
import com.manektech.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {
    private val _id = MutableLiveData<Int>()

    private val _restaurantItem = _id.switchMap { id ->
        repository.getRestaurant(id)
    }

    val restaurantItem: LiveData<Resource<RestaurantItem>> = _restaurantItem

    private val _imageListItem = _id.switchMap { id ->
        repository.getImageList(id)
    }
    val imageListItem: LiveData<Resource<List<Image>>> = _imageListItem

    fun start(id: Int) {
        _id.value = id
    }


}
