package com.mek.bootcampgraduationproject.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favori_yemekler")
@Parcelize
data class Yemekler(
    @SerializedName("yemek_adi")
    val yemekAdi: String?,
    @SerializedName("yemek_fiyat")
    val yemekFiyat: String?,
    @SerializedName("yemek_resim_adi")
    val yemekResimAdi: String?,
    @SerializedName("yemek_id")
    @PrimaryKey val yemekId: String
) : Parcelable