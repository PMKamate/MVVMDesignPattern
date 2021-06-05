package com.manektech.data.repository

import com.fasterxml.jackson.annotation.JsonProperty

class RestaurantListResponse {
    @JsonProperty("status")
    var status: Int? = null

    @JsonProperty("data")
    var data: List<RestaurantItemResponse>? = null
}