package com.mek.bootcampgraduationproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mek.bootcampgraduationproject.model.Yemekler

@Database(entities = [Yemekler::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealDao() : MealDao
}