package com.mek.bootcampgraduationproject.ui.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mek.bootcampgraduationproject.di.Repository
import com.mek.bootcampgraduationproject.di.RoomRepository
import com.mek.bootcampgraduationproject.model.Yemekler
import com.mek.bootcampgraduationproject.room.MealDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UrunDetayViewModel @Inject constructor(application: Application,private val repository: RoomRepository,private val repo : Repository) : AndroidViewModel(application) {

    private var isFavorited = MutableLiveData<Boolean>()

    fun addMealToFavorites(yemek: Yemekler) {
        viewModelScope.launch {
            repository.localDataSource.insertMeal(yemek)
            checkIfFavorited(yemek.yemekId.toInt())
        }
    }

    fun deleteMealFromFavorites(yemek: Yemekler){
        viewModelScope.launch {
            repository.localDataSource.deleteMeal(yemek)
            checkIfFavorited(yemek.yemekId.toInt())
        }
    }


    fun checkIfFavorited(id: Int) {
        viewModelScope.launch {
            isFavorited.value = repository.isMealFavorited(id)
        }
    }

    fun observeIsFavorited() : LiveData<Boolean>{
        return isFavorited
    }

    fun addToCart(yemek: Yemekler, adet: Int , kullaniciAdi: String = "emin_seyfi") {
        viewModelScope.launch {
            try {
                yemek.yemekAdi?.let { adi ->
                    yemek.yemekResimAdi?.let { resimAdi ->
                        val fiyat = yemek.yemekFiyat?.toIntOrNull() ?: 0
                        repo.remoteData.addMealToCart(adi, resimAdi, fiyat, adet, kullaniciAdi)

                    }
                }
            } catch (e: Exception) {
                val context = getApplication<Application>()
                Toast.makeText(context, "yemek sepete eklenirken bir hata oluştu", Toast.LENGTH_SHORT).show()
                Log.e("Sepet", "Sepete eklenirken hata: ${e.localizedMessage}")
            }
        }
    }



}