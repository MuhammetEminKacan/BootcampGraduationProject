package com.mek.bootcampgraduationproject.di

import com.mek.bootcampgraduationproject.model.FoodModel
import com.mek.bootcampgraduationproject.model.Yemekler
import com.mek.bootcampgraduationproject.retrofit.MealApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val mealApi : MealApi) {
    suspend fun getAllMeals() : FoodModel {
        return mealApi.getAllMeals()
    }

    suspend fun addMealToCart(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Int,
        yemekSiparisAdet: Int,
        kullaniciAdi: String
    ) = mealApi.addMealToCart(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)

    suspend fun getCardItems(kullaniciAdi: String) : FoodModel{
        return mealApi.getCartItems(kullaniciAdi)
    }
}