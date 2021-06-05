package com.manektech.data.repository

import com.fasterxml.jackson.annotation.JsonProperty

class RestaurantItemResponse {
    @JsonProperty("id")
    var id: Int? = null

    @JsonProperty("title")
    var title: String? = null

    @JsonProperty("phone_no")
    var phoneNo: Long? = null

    @JsonProperty("description")
    var description: String? = null

    @JsonProperty("rating")
    var rating: Int? = null

    @JsonProperty("address")
    var address: String? = null

    @JsonProperty("city")
    var city: String? = null

    @JsonProperty("state")
    var state: String? = null

    @JsonProperty("country")
    var country: String? = null

    @JsonProperty("pincode")
    var pincode: Int? = null

    @JsonProperty("long")
    var _long: String? = null

    @JsonProperty("lat")
    var lat: String? = null

    @JsonProperty("created_at")
    var createdAt: String? = null

    @JsonProperty("updated_at")
    var updatedAt: String? = null

    @JsonProperty("img")
    var img: ArrayList<ImageResponse>? = null
}
