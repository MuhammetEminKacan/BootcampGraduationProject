package com.mek.bootcampgraduationproject.model

import com.google.gson.annotations.SerializedName

data class SepetYemeklerResponse(
    @SerializedName("sepet_yemekler")
    val yemekler: List<SepetYemek>?,

    @SerializedName("success")
    val success: Int
)