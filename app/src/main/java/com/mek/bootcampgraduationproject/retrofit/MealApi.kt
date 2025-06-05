package com.mek.bootcampgraduationproject.retrofit

import com.mek.bootcampgraduationproject.model.FoodModel
import okhttp3.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealApi {

    @GET("tumYemekleriGetir.php")
    suspend fun getAllMeals() : FoodModel

    @FormUrlEncoded
    @POST("sepeteYemekEkle.php")
    suspend fun addMealToCart(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") yemekResimAdi: String,
        @Field("yemek_fiyat") yemekFiyat: Int,
        @Field("yemek_siparis_adet") yemekSiparisAdet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): FoodModel

    @GET("sepettekiYemekleriGetir.php")
    suspend fun getCartItems(@Query("kullanici_adi") kullaniciAdi : String) : FoodModel
}