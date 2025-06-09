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



     fun getMeals(kullaniciAdi : String){
        viewModelScope.launch {
            val response = repository.remoteData.getCardItems(kullaniciAdi)
            if (response.yemekler == null){
                Log.e("AlisverisSepetVM","null geldi")
            }else{
                Log.e("AlisverisSepetVM", "Sepet Response: ${response}")
            }
            meals.value = response.yemekler ?: emptyList()
        }
    }

    fun observeMealsLiveData() : LiveData<List<SepetYemek>> {
        return meals
    }


}