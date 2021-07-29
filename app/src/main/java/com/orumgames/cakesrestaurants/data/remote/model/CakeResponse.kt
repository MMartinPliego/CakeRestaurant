package com.orumgames.cakesrestaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class CakeResponse(
    var title: String,
    var desc: String,
    @SerializedName("image")
    var imageUrl: String
)
