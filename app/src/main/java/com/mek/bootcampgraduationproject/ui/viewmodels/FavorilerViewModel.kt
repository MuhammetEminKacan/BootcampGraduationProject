package com.mek.bootcampgraduationproject.ui.viewmodels

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
class FavorilerViewModel @Inject constructor(private val repository: RoomRepository) : ViewModel() {

    private val favoriYemekler = MutableLiveData<List<Yemekler>>()


    fun getAllFavoriteMeals() {
        viewModelScope.launch {
            repository.localDataSource.getAllMeals().collect{ favoriler ->
                favoriYemekler.postValue(favoriler)
            }
        }
    }

    fun observeFavoriler(): LiveData<List<Yemekler>> = favoriYemekler
}