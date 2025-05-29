package com.mek.bootcampgraduationproject.model


import com.google.gson.annotations.SerializedName

data class FoodModel(
    @SerializedName("success")
    val success: Int?,
    @SerializedName("yemekler")
    val yemekler: List<Yemekler?>?
)