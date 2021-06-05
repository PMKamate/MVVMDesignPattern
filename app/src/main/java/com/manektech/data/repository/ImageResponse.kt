package com.manektech.data.repository

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ImageResponse {
    @JsonProperty("id")
    var id: Int? = null

    @JsonProperty("main_id")
    var mainId: Int? = null

    @JsonProperty("image")
    var image: String? = null

    @JsonProperty("created_at")
    var createdAt: String? = null

    @JsonProperty("updated_at")
    var updatedAt: String? = null
}