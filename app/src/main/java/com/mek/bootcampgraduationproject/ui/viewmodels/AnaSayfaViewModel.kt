package com.mek.bootcampgraduationproject.ui.viewmodels

import android.util.Log
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
    private val repository : Repository,private val repo : RoomRepository) :ViewModel() {

    private val meals = MutableLiveData<List<Yemekler>>()
    val favoriYemeklerLiveData = MutableLiveData<List<Yemekler>>()
    private var isFavorited = MutableLiveData<Boolean>()


    init {
        getMeals()
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