package com.manektech.data.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.manektech.data.repository.ImageResponse
import com.manektech.utils.DataConverter
import java.io.Serializable


@Entity(tableName = "restaurantItem")
data class RestaurantItem(
    @PrimaryKey
    var id: Int? = null,
    var title: String? = null,
    var phone_no: Long? = null,
    var description: String? = null,
    var rating: Int? = null,
    var address: String? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
    var pincode: Int? = null,
    var long: String? = null,
    var lat: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
) {

    constructor() : this(
        0,
        "",
        0,
        "",
        0,
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        ""
    )
}

