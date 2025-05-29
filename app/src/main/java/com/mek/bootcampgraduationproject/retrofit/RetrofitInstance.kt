package com.mek.bootcampgraduationproject.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api : MealApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://kasimadalan.pe.hu/yemekler/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)
    }
}