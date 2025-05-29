package com.mek.bootcampgraduationproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.bootcampgraduationproject.di.Repository
import com.mek.bootcampgraduationproject.model.Yemekler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(
    private val repository : Repository) :ViewModel() {

    private val meals = MutableLiveData<List<Yemekler>>()

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            val response = repository.remoteData.getAllMeals()
            meals.value = response.yemekler?.filterNotNull()
        }
    }

    fun observeMealsLiveData() : LiveData<List<Yemekler>>{
        return meals
    }



}