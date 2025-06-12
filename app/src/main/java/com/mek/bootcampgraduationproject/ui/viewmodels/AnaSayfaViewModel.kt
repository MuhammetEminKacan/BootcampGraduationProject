package com.mek.bootcampgraduationproject.ui.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.bootcampgraduationproject.di.Repository
import com.mek.bootcampgraduationproject.di.RoomRepository
import com.mek.bootcampgraduationproject.model.Yemekler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(
    private val repository : Repository,private val repo : RoomRepository,application: Application) :AndroidViewModel(application) {

    private val meals = MutableLiveData<List<Yemekler>>()
    val favoriYemeklerLiveData = MutableLiveData<List<Yemekler>>()
    private var isFavorited = MutableLiveData<Boolean>()


    init {
        getMeals()
    }


    fun addToCart(yemek: Yemekler, adet: Int , kullaniciAdi: String = "emin_seyfi") {
        viewModelScope.launch {
            try {
                yemek.yemekAdi?.let { adi ->
                    yemek.yemekResimAdi?.let { resimAdi ->
                        val fiyat = yemek.yemekFiyat?.toIntOrNull() ?: 0
                        repository.remoteData.addMealToCart(adi, resimAdi, fiyat, adet, kullaniciAdi)
                        
                    }
                }
            } catch (e: Exception) {
                val context = getApplication<Application>()
                Toast.makeText(context, "yemek sepete eklenirken bir hata oluştu", Toast.LENGTH_SHORT).show()
                Log.e("Sepet", "Sepete eklenirken hata: ${e.localizedMessage}")
            }
        }
    }



    fun addMealToFavorites(yemek: Yemekler) {
        viewModelScope.launch {
            repo.localDataSource.insertMeal(yemek)
            checkIfFavorited(yemek.yemekId.toInt())
        }
    }

    fun deleteMealFromFavorites(yemek: Yemekler){
        viewModelScope.launch {
            repo.localDataSource.deleteMeal(yemek)
            checkIfFavorited(yemek.yemekId.toInt())
        }
    }

    fun checkIfFavorited(id: Int) {
        viewModelScope.launch {
            isFavorited.value = repo.isMealFavorited(id)
        }
    }


    private fun getMeals() {
        viewModelScope.launch {
            val response = repository.remoteData.getAllMeals()
            meals.value = response.yemekler?.filterNotNull()
        }
    }

    fun getAllFavoriteMeals() {
        viewModelScope.launch {
            repo.localDataSource.getAllMeals().collect { favoriler ->
                favoriYemeklerLiveData.postValue(favoriler)
            }
        }
    }

    fun observeMealsLiveData() : LiveData<List<Yemekler>>{
        return meals
    }

    fun observeFavoriler(): LiveData<List<Yemekler>> = favoriYemeklerLiveData




}