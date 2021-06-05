package com.manektech.data.remote

import javax.inject.Inject

class RestaurantRemoteDataSource @Inject constructor(
    private val restaurantService: RestaurantService
) : BaseDataSource() {
    suspend fun getAllRestaurantList() = getResult { restaurantService.getAllRestaurantListResponse() }
}