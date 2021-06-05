package com.manektech.data.remote

import com.manektech.data.repository.RestaurantListResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantService {
    @GET("restaurants_list")
    suspend fun getAllRestaurantListResponse() : Response<RestaurantListResponse>
}