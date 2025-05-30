package com.mek.bootcampgraduationproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mek.bootcampgraduationproject.model.Yemekler
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM favori_yemekler")
    fun getAllMeals() : Flow<List<Yemekler>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(yemek : Yemekler)

    @Delete
    suspend fun deleteMeal(yemek : Yemekler)

    @Query("SELECT * FROM favori_yemekler WHERE yemekId = :id LIMIT 1")
    suspend fun getMealById(id: Int): Yemekler?

}