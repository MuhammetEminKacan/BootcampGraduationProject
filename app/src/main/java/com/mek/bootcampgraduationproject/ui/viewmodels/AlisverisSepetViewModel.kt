package com.mek.bootcampgraduationproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.bootcampgraduationproject.di.Repository
import com.mek.bootcampgraduationproject.model.SepetYemek
import com.mek.bootcampgraduationproject.model.Yemekler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlisverisSepetViewModel @Inject constructor(private val repository : Repository) : ViewModel() {

    private val meals = MutableLiveData<List<SepetYemek>>()



    fun getMeals(kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val response = repository.remoteData.getCardItems(kullaniciAdi)
                meals.value = response.yemekler ?: emptyList()
            } catch (e: Exception) {
                Log.e("AlisverisSepetViewModel", "getMeals HATA: ${e.localizedMessage}")
                meals.value = emptyList()
            }
        }
    }

    fun deleteMealFromCards(yemekId : Int,kullaniciAdi: String){
        viewModelScope.launch {
            repository.remoteData.deleteMealFromCart(yemekId,kullaniciAdi)
            getMeals(kullaniciAdi)
        }
    }

    fun observeMealsLiveData() : LiveData<List<SepetYemek>> {
        return meals
    }


}