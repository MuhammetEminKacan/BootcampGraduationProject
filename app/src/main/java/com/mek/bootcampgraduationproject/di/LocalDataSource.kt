package com.mek.bootcampgraduationproject.di

import com.mek.bootcampgraduationproject.model.Yemekler
import com.mek.bootcampgraduationproject.room.MealDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(private val mealDao : MealDao) {

    fun getAllMeals() : Flow<List<Yemekler>>{
        return mealDao.getAllMeals()
    }

    suspend fun insertMeal(yemek : Yemekler){
        mealDao.insertMeal(yemek)
    }

    suspend fun deleteMeal(yemek: Yemekler){
        mealDao.deleteMeal(yemek)
    }

    suspend fun getMealById(id: Int): Yemekler? {
        return mealDao.getMealById(id)
    }

}