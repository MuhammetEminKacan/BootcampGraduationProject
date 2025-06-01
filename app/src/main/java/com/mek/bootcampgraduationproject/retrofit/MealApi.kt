package com.mek.bootcampgraduationproject.retrofit

import com.mek.bootcampgraduationproject.model.FoodModel
import retrofit2.http.GET

interface MealApi {

    @GET("tumYemekleriGetir.php")
    suspend fun getAllMeals() : FoodModel
}