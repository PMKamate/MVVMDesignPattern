package com.manektech.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "image")
data class Image(
    @PrimaryKey
    var id: Int?=null,
    var main_id: Int?=null,
    var image: String?=null,
    var created_at: String?=null,
    var updated_at: String?=null
) : Serializable